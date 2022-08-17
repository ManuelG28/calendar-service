package com.idea.calendarservice.db;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CalendarRepository extends PagingAndSortingRepository<CalendarEntity, Long> {

  List<CalendarEntity> findCalendarEntitiesByDate(String date);

}
