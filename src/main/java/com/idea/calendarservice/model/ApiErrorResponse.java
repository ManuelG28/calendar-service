package com.idea.calendarservice.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class ApiErrorResponse {

  int code;
  Object data;
  String stackTrace;
  LocalDateTime timestamp;
}
