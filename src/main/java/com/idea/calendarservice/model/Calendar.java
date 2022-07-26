package com.idea.calendarservice.model;

import static com.idea.calendarservice.utils.StringUtils.Formats.DATE_REGEX_FORMAT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.utils.StringUtils.ErrorResponses;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
public class Calendar {

  Long id;
  @Pattern(regexp = DATE_REGEX_FORMAT, message = ErrorResponses.DATE_MUST_MATCH_PATTERN)
  @NotEmpty( message = ErrorResponses.DATE_MUST_NOT_BE_EMPTY)
  String date;
  @NotEmpty( message = ErrorResponses.NAME_MUST_NOT_BE_EMPTY)
  String name;
  @NotEmpty( message = ErrorResponses.DESCRIPTION_MUST_NOT_BE_EMPTY)
  String description;

  public CalendarEntity toCalendarEntity() {
    return CalendarEntity.builder()
        .id(this.id)
        .date(this.date)
        .name(this.name)
        .description(this.description)
        .build();
  }
}
