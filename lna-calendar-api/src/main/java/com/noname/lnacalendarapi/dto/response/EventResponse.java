package com.noname.lnacalendarapi.dto.response;

import com.noname.lnacalendarapi.model.enumerated.EventType;
import com.noname.lnacalendarapi.model.enumerated.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private UUID id;

    private String title;

    private String description;

    private OffsetDateTime startTime;

    private OffsetDateTime endTime;

    private String location;

    private EventType eventType;

    private Set<String> tags;

    private Priority priority;

    private boolean allDay;

    private String recurrenceRule;

    private String notes;

    private boolean isCompleted;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}