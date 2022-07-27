package com.idea.calendarservice.utils;

public class StringUtils {

  public static class Formats {

    public static final String DATE_REGEX_FORMAT = "\\d{2}-\\d{2}"; //dd-MM e.g: 02-12 (December 2)
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Formats() {
    }
  }

  public static class ErrorResponses {

    public static final String DATE_MUST_MATCH_PATTERN = "Must match dd-MM format. e.g: 02-12";

    private ErrorResponses() {
    }
  }

  private StringUtils() {
  }
}
