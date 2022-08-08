package com.idea.calendarservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
@JsonInclude(Include.NON_NULL)
public class ApiErrorResponse {

  int code;
  Object data;
  String stackTrace;
  LocalDateTime timestamp;
}
