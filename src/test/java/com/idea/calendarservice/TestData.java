package com.idea.calendarservice;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.model.Calendar;
import java.util.UUID;

public class TestData {

  public static final UUID ID = UUID.fromString("bbb3c2b0-68e2-444b-971c-532e09820b91");

  public static final Calendar CALENDAR = Calendar.builder()
      .date("12-12")
      .description(
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse facilisis accumsan nibh nec convallis. Morbi.")
      .name("Lorem ipsum dolor.")
      .build();

  public static final Calendar CALENDAR_WITH_WRONG_ARGUMENTS = Calendar.builder()
      .date("112123-12")
      .name("Lorem ipsum dolor.")
      .build();

  public static final CalendarEntity CALENDAR_ENTITY = CalendarEntity.builder()
      .id(ID)
      .date("12-12")
      .description(
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse facilisis accumsan nibh nec convallis. Morbi.")
      .name("Lorem ipsum dolor.")
      .build();
}
