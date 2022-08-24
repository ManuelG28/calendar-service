package com.idea.calendarservice.service;

import static com.idea.calendarservice.TestData.CALENDAR;
import static com.idea.calendarservice.TestData.CALENDAR_ENTITY;
import static com.idea.calendarservice.TestData.ID;
import static com.idea.calendarservice.TestData.UPDATED_CALENDAR;
import static com.idea.calendarservice.TestData.generateCalendarEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.db.CalendarRepository;
import com.idea.calendarservice.exception.CalendarEntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class CalendarServiceTest {

  @InjectMocks
  private CalendarServiceImpl calendarService;

  @Mock
  private CalendarRepository calendarRepository;

  @Test
  void should_be_able_to_create_a_new_calendar() {
    when(calendarRepository.save(any(CalendarEntity.class))).thenReturn(CALENDAR_ENTITY);
    CalendarEntity createdCalendar = calendarService.createCalendar(CALENDAR);

    assertThat(createdCalendar).isEqualTo(CALENDAR_ENTITY);
  }

  @Test
  void should_be_able_to_get_all_calendars() {
    when(calendarRepository.findAll()).thenReturn(List.of(CALENDAR_ENTITY));

    assertThat(calendarService.getAllCalendars()).allSatisfy(calendar -> {
      assertThat(calendar.getId()).isNotNull();
      assertThat(calendar.getName()).isNotEmpty();
      assertThat(calendar.getDate()).isNotEmpty();
      assertThat(calendar.getDescription()).isNotEmpty();
    });
  }

  @Test
  void should_be_able_to_get_pageable_calendars() {
    when(calendarRepository.findAll(PageRequest.of(0, 10))).thenReturn(
        new PageImpl<>(generateCalendarEntities()));

    assertThat(calendarService.getCalendarsPaginated(0))
        .hasSize(10)
        .allSatisfy(calendar -> {
          assertThat(calendar.getId()).isNotNull();
          assertThat(calendar.getName()).isNotEmpty();
          assertThat(calendar.getDate()).isNotEmpty();
          assertThat(calendar.getDescription()).isNotEmpty();
        });
  }

  @Test
  void should_be_able_to_get_calendars_by_date() {
    when(calendarRepository.findCalendarEntitiesByDate(any(String.class))).thenReturn(
        List.of(CALENDAR_ENTITY));

    assertThat(calendarService.getCalendarsByDate(CALENDAR_ENTITY.getDate())).allSatisfy(
        calendar -> {
          assertThat(calendar.getId()).isNotNull();
          assertThat(calendar.getName()).isNotEmpty();
          assertThat(calendar.getDescription()).isNotEmpty();
          assertThat(calendar.getDate()).isEqualTo(CALENDAR_ENTITY.getDate());
        });
  }

  @Test
  void should_be_able_to_get_calendar_by_its_id() {
    when(calendarRepository.findById(ID)).thenReturn(Optional.of(CALENDAR_ENTITY));
    assertThat(calendarService.getCalendarById(ID)).isEqualTo(CALENDAR_ENTITY);
  }

  @Test
  void should_be_able_to_update_calendars() {
    calendarService.updateCalendar(UPDATED_CALENDAR);
    verify(calendarRepository, atLeastOnce()).save(any(CalendarEntity.class));
  }

  @Test
  void given_a_not_stored_id_should_return_not_found() {
    when(calendarRepository.findById(ID)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> {
      calendarService.deleteCalendarById(ID);
    }).isInstanceOf(CalendarEntityNotFoundException.class);
  }

  @Test
  void should_be_able_to_delete_calendar_by_its_id() {
    when(calendarRepository.findById(ID)).thenReturn(Optional.of(CALENDAR_ENTITY));
    calendarService.deleteCalendarById(ID);
    verify(calendarRepository, atLeastOnce()).delete(CALENDAR_ENTITY);
  }

  @Test
  void should_be_able_to_get_total_of_calendars(){
    when(calendarRepository.count()).thenReturn(20L);
    assertThat(calendarService.getNumberOfCalendars()).isEqualTo(20L);
  }

}
