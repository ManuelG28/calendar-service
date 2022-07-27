package com.idea.calendarservice.model;

import static com.idea.calendarservice.utils.StringUtils.Formats.DATE_REGEX_FORMAT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.utils.StringUtils.ErrorResponses;
import java.util.UUID;
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

  UUID id;
  @Pattern(regexp = DATE_REGEX_FORMAT, message = ErrorResponses.DATE_MUST_MATCH_PATTERN)
  @NotEmpty
  String date;
  @NotEmpty
  String name;
  @NotEmpty
  String description;

  public CalendarEntity toDateEntity() {
    return CalendarEntity.builder()
        .id(this.id)
        .date(this.date)
        .name(this.name)
        .description(this.description)
        .build();
  }
}
