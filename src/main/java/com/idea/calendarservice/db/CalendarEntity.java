package com.idea.calendarservice.db;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.idea.calendarservice.model.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Jacksonized
@Table(name = CalendarEntity.CALENDAR_TABLE_NAME)
@Entity
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = {@JsonCreator(mode = JsonCreator.Mode.DELEGATING)})
@Getter
@Setter
@ToString
public class CalendarEntity {

  public static final String CALENDAR_TABLE_NAME = "calendar";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @NotNull
  private String date;

  @Column
  @NotNull
  private String name;

  @Column
  @NotNull
  private String description;

  public Calendar toCalendarModel() {
    return Calendar.builder()
        .id(this.id)
        .description(this.description)
        .name(this.name)
        .date(this.date)
        .build();
  }
}
