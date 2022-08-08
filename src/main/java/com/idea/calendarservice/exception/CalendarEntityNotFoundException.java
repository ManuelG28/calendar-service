package com.idea.calendarservice.exception;

public class CalendarEntityNotFoundException extends EntityNotFoundException{

  public CalendarEntityNotFoundException(String field, String value) {
    super("Calendar", field, value);
  }

}
