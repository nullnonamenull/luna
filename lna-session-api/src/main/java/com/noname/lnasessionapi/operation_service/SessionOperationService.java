package com.noname.lnasessionapi.operation_service;


import com.noname.lnasessionapi.data.Session;
import com.noname.lnasessionapi.repository.SessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionOperationService {

    private final SessionRepository sessionRepository;

    @Transactional
    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }
}
