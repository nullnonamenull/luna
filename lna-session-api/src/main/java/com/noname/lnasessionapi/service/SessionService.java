package com.noname.lnasessionapi.service;

import com.noname.lnasessionapi.data.Session;
import com.noname.lnasessionapi.operation_service.SessionOperationService;
import com.noname.lnasessionapi.util.MappingUtils;
import com.noname.lnasessiondto.SessionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionOperationService sessionOperationService;

    public SessionResponseDTO createSession() {
        Session session = Session.builder()
                .sessionId(UUID.randomUUID())
                .build();

        SessionResponseDTO result;
        try {
            result = MappingUtils.sessionToSessionResponseDTO(sessionOperationService.saveSession(session));
        } catch (Exception e) {
            log.error("An error occurred while saving or mapping the session: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save or map the session. Please check the logs for details.", e);
        }

        return result;
    }

}