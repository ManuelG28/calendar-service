package com.idea.calendarservice.service;

import static com.idea.calendarservice.TestData.CALENDAR;
import static com.idea.calendarservice.TestData.CALENDAR_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.db.CalendarRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalendarServiceTest {

  @InjectMocks
  private CalendarServiceImpl calendarService;

  @Mock
  private CalendarRepository calendarRepository;

  @Test
  void should_be_able_to_create_a_new_calendar(){
    when(calendarRepository.save(any(CalendarEntity.class))).thenReturn(CALENDAR_ENTITY);
    CalendarEntity createdCalendar = calendarService.createCalendar(CALENDAR);

    assertThat(createdCalendar).isEqualTo(CALENDAR_ENTITY);
  }

  @Test
  void shouldBeAbleToGetAllCalendars() {
    when(calendarRepository.findAll()).thenReturn(List.of(CALENDAR_ENTITY));
    assertThat(calendarService.getAllCalendars()).hasSize(1);
    assertThat(calendarService.getAllCalendars()).allSatisfy(calendar -> {
      assertThat(calendar.getId()).isNotNull();
      assertThat(calendar.getName()).isNotEmpty();
      assertThat(calendar.getDescription()).isNotEmpty();
      assertThat(calendar.getDescription()).isNotEmpty();
    });
  }

}
