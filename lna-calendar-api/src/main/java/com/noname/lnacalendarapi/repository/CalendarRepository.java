package com.noname.lnacalendarapi.repository;

import com.noname.lnacalendarapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CalendarRepository extends JpaRepository<Event, UUID> {
}
