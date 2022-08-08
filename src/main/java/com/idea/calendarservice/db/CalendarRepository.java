package com.idea.calendarservice.db;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<CalendarEntity, Long> {

  List<CalendarEntity> findCalendarEntitiesByDate(String date);

}
