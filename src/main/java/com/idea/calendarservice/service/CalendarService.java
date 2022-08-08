package com.idea.calendarservice.service;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.model.Calendar;
import java.util.List;

public interface CalendarService {

  CalendarEntity createCalendar(Calendar calendar);

  List<Calendar> getAllCalendars();

  List<Calendar> getCalendarsByDate(String date);

  CalendarEntity getCalendarById(Long id);

  void deleteCalendarById(Long id);
}
