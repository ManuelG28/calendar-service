package com.idea.calendarservice;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.model.Calendar;
import java.util.Random;

public class TestData {

  public static final Long ID = new Random().nextLong();

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

  public static final Calendar UPDATED_CALENDAR = Calendar.builder()
      .id(ID)
      .date("24-12")
      .description(
          "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
      .name("Lorem ipsum dolor sit amet.")
      .build();

  public static final CalendarEntity UPDATED_CALENDAR_ENTITY = UPDATED_CALENDAR.toCalendarEntity();

  public static final CalendarEntity CALENDAR_ENTITY = CalendarEntity.builder()
      .id(ID)
      .date("12-12")
      .description(
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse facilisis accumsan nibh nec convallis. Morbi.")
      .name("Lorem ipsum dolor.")
      .build();
}
