package com.idea.calendarservice.db;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<CalendarEntity, UUID> {

  List<CalendarEntity> findCalendarEntitiesByDate(String date);

}
