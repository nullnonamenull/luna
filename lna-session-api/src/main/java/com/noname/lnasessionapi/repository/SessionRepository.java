package com.noname.lnasessionapi.repository;

import com.noname.lnasessionapi.data.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> getSessionsByClosedAtNull();

    Optional<Session> getSessionById(UUID id);

}