package com.idea.calendarservice.controller;


import static com.idea.calendarservice.TestData.CALENDAR;
import static com.idea.calendarservice.TestData.CALENDAR_ENTITY;
import static com.idea.calendarservice.TestData.CALENDAR_WITH_WRONG_ARGUMENTS;
import static com.idea.calendarservice.TestData.ID;
import static com.idea.calendarservice.TestData.UPDATED_CALENDAR;
import static com.idea.calendarservice.TestData.generateCalendars;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idea.calendarservice.exception.CalendarEntityNotFoundException;
import com.idea.calendarservice.model.Calendar;
import com.idea.calendarservice.service.CalendarService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RunWith(SpringRunner.class)
@WebMvcTest(CalendarController.class)
class CalendarControllerTest extends CalendarControllerBaseTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CalendarService calendarService;

  @Test
  void should_be_able_to_create_new_calendars() throws Exception {
    given(calendarService.createCalendar(CALENDAR)).willReturn(CALENDAR_ENTITY);
    mockMvc.perform(
        post(baseUrl)
            .content(objectMapper.writeValueAsString(CALENDAR))
            .contextPath("/api")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpect(status().isCreated());
  }

  @Test
  void given_bad_calendar_arguments_when_post_calendars_should_return_bad_request()
      throws Exception {
    mockMvc.perform(
            post(baseUrl)
                .content(objectMapper.writeValueAsString(CALENDAR_WITH_WRONG_ARGUMENTS))
                .contextPath("/api")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(
            MethodArgumentNotValidException.class));
  }

  @Test
  void should_be_able_to_get_all_calendars() throws Exception {
    List<Calendar> expectedCalendars = List.of(CALENDAR);
    given(calendarService.getAllCalendars()).willReturn(List.of(CALENDAR));
    mockMvc.perform(
            get(baseUrl)
                .contextPath("/api"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedCalendars)));
  }

  @Test
  void should_be_able_to_get_calendars_paginated() throws Exception{
    List<Calendar> calendarsAtFirstPage = generateCalendars();
    List<Calendar> calendarsAtSecondPage = generateCalendars();

    given(calendarService.getCalendarsPaginated(0)).willReturn(calendarsAtFirstPage);
    given(calendarService.getCalendarsPaginated(1)).willReturn(calendarsAtSecondPage);

    mockMvc.perform(
            get(baseUrl)
                .contextPath("/api")
                .param("page", "0"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(calendarsAtFirstPage)));

    mockMvc.perform(
            get(baseUrl)
                .contextPath("/api")
                .param("page", "1"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(calendarsAtSecondPage)));
  }

  @Test
  void should_be_able_to_get_calendars_by_date() throws Exception {
    List<Calendar> expectedCalendars = List.of(CALENDAR);
    given(calendarService.getCalendarsByDate(any(String.class))).willReturn(List.of(CALENDAR));
    mockMvc.perform(
            get(baseUrl.concat("/date/" + CALENDAR.getDate())).contextPath("/api"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedCalendars)));
  }

  @Test
  void should_be_abe_to_update_calendars() throws Exception {
    doNothing().when(calendarService).updateCalendar(UPDATED_CALENDAR);
    mockMvc.perform(
        put(baseUrl)
            .content(objectMapper.writeValueAsString(UPDATED_CALENDAR))
            .contextPath("/api")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpect(status().isNoContent());
  }

  @Test
  void given_bad_calendar_arguments_when_put_calendars_should_return_bad_request()
      throws Exception {
    mockMvc.perform(
            put(baseUrl)
                .content(objectMapper.writeValueAsString(CALENDAR_WITH_WRONG_ARGUMENTS))
                .contextPath("/api")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(
            MethodArgumentNotValidException.class));
  }

  @Test
  void should_be_able_to_delete_calendar_by_its_id() throws Exception {
    given(calendarService.getCalendarById(ID)).willReturn(CALENDAR_ENTITY);
    doNothing().when(calendarService).deleteCalendarById(ID);
    mockMvc.perform(
            delete(baseUrl.concat("/" + ID)).contextPath("/api"))
        .andExpect(status().isNoContent());
  }

  @Test
  void given_a_not_stored_id_should_return_not_found() throws Exception {
    doThrow(new CalendarEntityNotFoundException("id", ID.toString())).when(calendarService).deleteCalendarById(ID);
    mockMvc.perform(
            delete(baseUrl.concat("/" + ID)).contextPath("/api"))
        .andExpect(status().isNotFound());
  }
}
