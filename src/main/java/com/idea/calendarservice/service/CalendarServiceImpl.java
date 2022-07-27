package com.idea.calendarservice.service;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.db.CalendarRepository;
import com.idea.calendarservice.model.Calendar;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {

  CalendarRepository calendarRepository;

  @Override
  public CalendarEntity createCalendar(Calendar calendar) {
    return calendarRepository.save(calendar.toDateEntity());
  }
}
