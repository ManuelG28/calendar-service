package com.idea.calendarservice.exception;

import com.google.common.base.Throwables;
import com.idea.calendarservice.model.ApiErrorResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    List<String> errors = new ArrayList<>();
    for (var error : ex.getBindingResult().getAllErrors()) {
      String errorMessage = error.getDefaultMessage();
      errors.add(errorMessage);
    }

    ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .errors(errors)
        .stackTrace(Throwables.getStackTraceAsString(ex))
        .timestamp(LocalDateTime.now())
        .build();
    return ResponseEntity.badRequest().body(apiErrorResponse);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex, WebRequest request){
    ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
        .code(HttpStatus.NOT_FOUND.value())
        .errors(List.of(ex.getMessage()))
        .stackTrace(Throwables.getStackTraceAsString(ex))
        .timestamp(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
  }
}
