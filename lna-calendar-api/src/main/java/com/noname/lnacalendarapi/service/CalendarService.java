package com.noname.lnacalendarapi.service;

import com.noname.lnacalendarapi.dto.request.EventRequest;
import com.noname.lnacalendarapi.dto.response.EventResponse;
import com.noname.lnacalendarapi.repository.CalendarRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public EventResponse updateEvent(UUID id, @Valid EventRequest eventRequest) {
        return null;
    }

    public void deleteEvent(UUID id) {
    }

    public EventResponse getEvent(UUID id) {

        return null;
    }

    public List<EventResponse> listEvents(String userId, String filter) {
        return null;
    }

    public boolean checkConflicts(@Valid EventRequest eventRequest) {
        return false;
    }

    public List<EventResponse> getUpcomingEvents(String userId) {
        return null;
    }

    public EventResponse createEvent(@Valid EventRequest eventRequest) {
        return null;
    }
}
