package com.idea.calendarservice.exception;

import com.idea.calendarservice.utils.StringUtils.Exception;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String entity, String field, String value) {
    super(String.format(Exception.ENTITY_NOT_FOUND_BY_ID, entity, field, value));
  }
}
