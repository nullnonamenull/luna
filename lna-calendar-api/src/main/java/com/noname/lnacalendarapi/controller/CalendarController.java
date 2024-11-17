package com.noname.lnacalendarapi.controller;

import com.noname.lnacalendarapi.dto.request.EventRequest;
import com.noname.lnacalendarapi.dto.response.EventResponse;
import com.noname.lnacalendarapi.service.CalendarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * Endpoint to create a new event.
     */
    @PostMapping("/events")
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        EventResponse createdEvent = calendarService.createEvent(eventRequest);
        return ResponseEntity.ok(createdEvent);
    }

    /**
     * Endpoint to update an existing event.
     */
    @PutMapping("/events/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable UUID id,
                                                     @Valid @RequestBody EventRequest eventRequest) {
        EventResponse updatedEvent = calendarService.updateEvent(id, eventRequest);
        return ResponseEntity.ok(updatedEvent);
    }

    /**
     * Endpoint to delete an event by ID.
     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        calendarService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint to get details of a specific event.
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable UUID id) {
        EventResponse event = calendarService.getEvent(id);
        return ResponseEntity.ok(event);
    }

    /**
     * Endpoint to list all events for a user.
     */
    @GetMapping("/events")
    public ResponseEntity<List<EventResponse>> listEvents(@RequestParam(required = false) String userId,
                                                          @RequestParam(required = false) String filter) {
        List<EventResponse> events = calendarService.listEvents(userId, filter);
        return ResponseEntity.ok(events);
    }

    /**
     * Endpoint to check for conflicts with a proposed event time.
     */
    @PostMapping("/events/conflicts")
    public ResponseEntity<Boolean> checkConflicts(@Valid @RequestBody EventRequest eventRequest) {
        boolean hasConflict = calendarService.checkConflicts(eventRequest);
        return ResponseEntity.ok(hasConflict);
    }

    /**
     * Endpoint to get upcoming events for scheduling triggers.
     */
    @GetMapping("/events/upcoming")
    public ResponseEntity<List<EventResponse>> getUpcomingEvents(@RequestParam(required = false) String userId) {
        List<EventResponse> upcomingEvents = calendarService.getUpcomingEvents(userId);
        return ResponseEntity.ok(upcomingEvents);
    }
}
