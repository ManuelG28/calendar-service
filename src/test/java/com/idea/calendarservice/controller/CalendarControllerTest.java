package com.idea.calendarservice.controller;


import static com.idea.calendarservice.TestData.CALENDAR;
import static com.idea.calendarservice.TestData.CALENDAR_ENTITY;
import static com.idea.calendarservice.TestData.CALENDAR_WITH_WRONG_ARGUMENTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class CalendarControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CalendarService calendarService;

  @Test
  void should_be_able_to_create_a_new_calendar() throws Exception {
    given(calendarService.createCalendar(CALENDAR)).willReturn(CALENDAR_ENTITY);
    mockMvc.perform(
        post("/api/" + Endpoints.V_1 + Endpoints.CALENDARS)
            .content(objectMapper.writeValueAsString(CALENDAR))
            .contextPath("/api")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
    ).andExpect(status().isCreated());
  }

  @Test
  void given_bad_calendar_arguments_should_return_bad_request() throws Exception {
    mockMvc.perform(
            post("/api/" + Endpoints.V_1 + Endpoints.CALENDARS)
                .content(objectMapper.writeValueAsString(CALENDAR_WITH_WRONG_ARGUMENTS))
                .contextPath("/api")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(
            MethodArgumentNotValidException.class));
  }

  @Test
  void shouldBeAbleToGetAllCalendars() throws Exception {
    List<Calendar> expectedCalendars = List.of(CALENDAR);
    given(calendarService.getAllCalendars()).willReturn(List.of(CALENDAR));
    mockMvc.perform(
            get("/api/" + Endpoints.V_1 + Endpoints.CALENDARS)
                .contextPath("/api"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedCalendars)));
  }
}
