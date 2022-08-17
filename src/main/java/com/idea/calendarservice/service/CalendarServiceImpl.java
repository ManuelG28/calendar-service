package com.idea.calendarservice.service;

import com.google.common.collect.Lists;
import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.db.CalendarRepository;
import com.idea.calendarservice.exception.CalendarEntityNotFoundException;
import com.idea.calendarservice.model.Calendar;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {

  private final CalendarRepository calendarRepository;

  @Override
  public CalendarEntity createCalendar(Calendar calendar) {
    return calendarRepository.save(calendar.toCalendarEntity());
  }

  @Override
  public List<Calendar> getAllCalendars() {
    return mapCalendarEntitiesToCalendars(calendarRepository.findAll());
  }

  @Override
  public List<Calendar> getCalendarsByDate(String date) {
    return mapCalendarEntitiesToCalendars(calendarRepository.findCalendarEntitiesByDate(date));
  }

  @Override
  public List<Calendar> getCalendarsPaginated(int page) {
    Pageable pageWithTenElements = PageRequest.of(page, 10);
    return mapCalendarEntitiesToCalendars(calendarRepository.findAll(pageWithTenElements));
  }

  @Override
  public CalendarEntity getCalendarById(Long id) {
    return calendarRepository.findById(id)
        .orElseThrow(() -> new CalendarEntityNotFoundException("id", id.toString()));
  }

  @Override
  public void updateCalendar(Calendar calendar) {
    calendarRepository.save(calendar.toCalendarEntity());
  }

  @Override
  public void deleteCalendarById(Long id) {
    calendarRepository.delete(getCalendarById(id));
  }

  private List<Calendar> mapCalendarEntitiesToCalendars(Iterable<CalendarEntity> calendarEntities) {
    return StreamEx.of(Lists.newArrayList(calendarEntities))
        .map(CalendarEntity::toCalendarModel).toList();
  }
}
