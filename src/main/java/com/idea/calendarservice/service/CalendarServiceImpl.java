package com.idea.calendarservice.service;

import com.google.common.collect.Lists;
import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.db.CalendarRepository;
import com.idea.calendarservice.model.Calendar;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {

  private CalendarRepository calendarRepository;

  @Override
  public CalendarEntity createCalendar(Calendar calendar) {
    return calendarRepository.save(calendar.toDateEntity());
  }

  @Override
  public List<Calendar> getAllCalendars() {
    return mapCalendarEntitiesToCalendars(calendarRepository.findAll());
  }

  @Override
  public List<Calendar> getCalendarsByDate(String date){
    return mapCalendarEntitiesToCalendars(calendarRepository.findCalendarEntitiesByDate(date));
  }

  private List<Calendar> mapCalendarEntitiesToCalendars(Iterable<CalendarEntity> calendarEntities){
    return StreamEx.of(Lists.newArrayList(calendarEntities))
            .map(CalendarEntity::toCalendarModel).toList();
  }
}
