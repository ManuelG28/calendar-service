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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping(V_1 + CALENDARS)
public class CalendarController {

  CalendarService calendarService;

  @PostMapping()
  public ResponseEntity<Void> addDate(@Valid @RequestBody Calendar calendar,
      UriComponentsBuilder uriComponentsBuilder) {
    CalendarEntity createdCalendar = calendarService.createCalendar(calendar);
    URI uri = uriComponentsBuilder.path("/dates/{id}").buildAndExpand(createdCalendar.getId())
        .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<List<Calendar>> getAllDates() {
    return ResponseEntity.ok(calendarService.getAllCalendars());
  }

}
