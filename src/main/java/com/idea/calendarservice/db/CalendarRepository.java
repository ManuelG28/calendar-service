package com.idea.calendarservice.db;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<CalendarEntity, UUID> {

  Optional<CalendarEntity> findCalendarEntityByDate(String date);

}
