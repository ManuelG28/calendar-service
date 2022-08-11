package com.idea.calendarservice.utils;

public class StringUtils {

  public static class Formats {

    public static final String DATE_REGEX_FORMAT = "\\d{2}-\\d{2}"; //dd-MM e.g: 02-12 (December 2)
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Formats() {
    }
  }

  public static class ErrorResponses {

    public static final String DATE_MUST_MATCH_PATTERN = "Date must match dd-MM format. e.g: 02-12";
    public static final String DATE_MUST_NOT_BE_EMPTY ="Date must not be empty";

    public static final String NAME_MUST_NOT_BE_EMPTY ="Name must not be empty";

    public static final String DESCRIPTION_MUST_NOT_BE_EMPTY ="Description must not be empty";

    private ErrorResponses() {
    }
  }

  public static class Exception {

    public static final String ENTITY_NOT_FOUND_BY_ID = "%s entity not found by %s %s";

    private Exception() {
    }
  }

  private StringUtils() {
  }
}
