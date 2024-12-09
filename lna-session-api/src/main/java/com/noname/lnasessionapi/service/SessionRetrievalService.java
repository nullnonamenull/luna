package com.noname.lnasessionapi.service;


import com.noname.lnasessionapi.data.Session;
import com.noname.lnasessionapi.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionRetrievalService {

    private final SessionRepository sessionRepository;


    public List<Session> getAllSessions() {
        return sessionRepository.getSessionsByClosedAtNull();
    }

    public Session getSessionWithAllMessages(final UUID sessionId) {
        return sessionRepository.getSessionById(sessionId)
                .orElseThrow();
    }

}