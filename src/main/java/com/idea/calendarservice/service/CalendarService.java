package com.idea.calendarservice.service;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.model.Calendar;

public interface CalendarService {

  CalendarEntity createCalendar(Calendar calendar);
}
