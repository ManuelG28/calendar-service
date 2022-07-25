package com.idea.calendarservice.db;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(
      name = "uuid2",
      strategy = "uuid2"
  )
  @Type(type = "uuid-char")
  @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
  private UUID id;

  @Column
  @NotNull
  private String date;

  @Column
  @NotNull
  private String name;

  @Column
  @NotNull
  private String description;
}
