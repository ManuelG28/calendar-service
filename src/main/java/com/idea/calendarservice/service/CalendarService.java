package com.idea.calendarservice.service;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.model.Calendar;
import java.util.List;

public interface CalendarService {

  CalendarEntity createCalendar(Calendar calendar);

  List<Calendar> getAllCalendars();

  List<Calendar> getCalendarsByDate(String date);

  List<Calendar> getCalendarsPaginated(int page);

  CalendarEntity getCalendarById(Long id);

  Long getNumberOfCalendars();

  void updateCalendar(Calendar calendar);

  void deleteCalendarById(Long id);
}
