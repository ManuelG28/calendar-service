package com.idea.calendarservice.controller;


import static com.idea.calendarservice.controller.Endpoints.CALENDARS;
import static com.idea.calendarservice.controller.Endpoints.V_1;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.model.Calendar;
import com.idea.calendarservice.service.CalendarService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping(V_1 + CALENDARS)
public class CalendarController {

  private final CalendarService calendarService;

  @PostMapping
  public ResponseEntity<Void> addCalendar(@Valid @RequestBody Calendar calendar,
      UriComponentsBuilder uriComponentsBuilder) {
    CalendarEntity createdCalendar = calendarService.createCalendar(calendar);
    URI uri = uriComponentsBuilder.path("/calendars/{id}").buildAndExpand(createdCalendar.getId())
        .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<List<Calendar>> getAllCalendars() {
    return ResponseEntity.ok(calendarService.getAllCalendars());
  }

  @GetMapping(params = {"page"})
  public ResponseEntity<List<Calendar>> getCalendarsPaginated(@RequestParam("page") int page) {
    return ResponseEntity.ok(calendarService.getCalendarsPaginated(page));
  }

  @GetMapping("/date/{date}")
  public ResponseEntity<List<Calendar>> getCalendarByDate(@PathVariable String date) {
    return ResponseEntity.ok(calendarService.getCalendarsByDate(date));
  }

  @PutMapping
  public ResponseEntity<Void> updateCalendar(@Valid @RequestBody Calendar calendar) {
    calendarService.updateCalendar(calendar);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCalendarById(@PathVariable Long id) {
    calendarService.deleteCalendarById(id);
    return ResponseEntity.noContent().build();
  }
}
