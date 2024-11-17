package com.noname.lnacalendarapi.model;

import com.noname.lnacalendarapi.model.enumerated.EventType;
import com.noname.lnacalendarapi.model.enumerated.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Event title cannot be empty")
    @Size(max = 255, message = "Event title must be at most 255 characters")
    private String title;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Start time is required")
    private OffsetDateTime startTime;

    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    private OffsetDateTime endTime;

    @Size(max = 255, message = "Location must be at most 255 characters")
    private String location;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Event type is required")
    private EventType eventType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "event_tags", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "tag")
    private Set<String> tags;

    @NotNull(message = "Priority is required")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(nullable = false)
    private boolean allDay;

    private String recurrenceRule;

    @Size(max = 500, message = "Notes must be at most 500 characters")
    private String notes;

    @Column(nullable = false)
    private boolean isCompleted;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}