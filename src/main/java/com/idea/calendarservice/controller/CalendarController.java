package com.idea.calendarservice.controller;


import static com.idea.calendarservice.controller.Endpoints.CALENDARS;
import static com.idea.calendarservice.controller.Endpoints.V_1;

import com.idea.calendarservice.db.CalendarEntity;
import com.idea.calendarservice.model.ApiErrorResponse;
import com.idea.calendarservice.model.Calendar;
import com.idea.calendarservice.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(summary = "Create a calendar")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Calendar created successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid calendar object supplied", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponse.class))})
  })
  @PostMapping
  public ResponseEntity<Void> addCalendar(@Valid @RequestBody Calendar calendar,
      UriComponentsBuilder uriComponentsBuilder) {
    CalendarEntity createdCalendar = calendarService.createCalendar(calendar);
    URI uri = uriComponentsBuilder.path("/calendars/{id}").buildAndExpand(createdCalendar.getId())
        .toUri();
    return ResponseEntity.created(uri).build();
  }

  @Operation(summary = "Get calendar paginated")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Calendars retrieve successfully"),
  })
  @GetMapping(params = {"page"})
  public ResponseEntity<List<Calendar>> getCalendarsPaginated(@RequestParam("page") int page) {
    return ResponseEntity.ok(calendarService.getCalendarsPaginated(page));
  }

  @GetMapping
  public ResponseEntity<List<Calendar>> getAllCalendars() {
    return ResponseEntity.ok(calendarService.getAllCalendars());
  }

  @Operation(summary = "Get calendar by its date")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Calendars retrieve successfully"),
  })
  @GetMapping("/date/{date}")
  public ResponseEntity<List<Calendar>> getCalendarByDate(@PathVariable String date) {
    return ResponseEntity.ok(calendarService.getCalendarsByDate(date));
  }

  @Operation(summary = "Update a calendar")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Calendar updated successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid calendar object supplied", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponse.class))})
  })
  @PutMapping
  public ResponseEntity<Void> updateCalendar(@Valid @RequestBody Calendar calendar) {
    calendarService.updateCalendar(calendar);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Delete a calendar by its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Calendar deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Calendar not found", content = {
          @Content(mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorResponse.class))})
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCalendarById(@PathVariable Long id) {
    calendarService.deleteCalendarById(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Get the total number of calendars")
  @ApiResponse(responseCode = "200", description = "Number of calendar retrieve successfully")
  @GetMapping("/total")
  public ResponseEntity<Long> getTotalCalendars() {
    return ResponseEntity.ok(calendarService.getNumberOfCalendars());
  }
}
