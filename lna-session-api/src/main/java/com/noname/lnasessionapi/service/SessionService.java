package com.noname.lnasessionapi.service;

import com.noname.lnasessionapi.data.Session;
import com.noname.lnasessionapi.util.MappingUtils;
import com.noname.lnasessiondto.SessionMessagesResponseDTO;
import com.noname.lnasessiondto.SessionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionOperationService sessionOperationService;
    private final SessionRetrievalService sessionRetrievalService;

    public SessionResponseDTO createSession() {
        Session session = Session.builder()
                .sessionName("New Chat Session")
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

    public List<SessionResponseDTO> getSessionResponseDTOList() {
        List<Session> sessions = sessionRetrievalService.getAllSessions();

        List<SessionResponseDTO> result;
        try {
            result = sessions.stream()
                    .map(MappingUtils::sessionToSessionResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while mapping the sessions.");
            throw new RuntimeException("Failed to map the session. Please check the logs for details.", e);
        }

        return result;
    }

    public SessionMessagesResponseDTO getSessionMessagesResponseDTO(final UUID sessionId) {
        Session sessionWithMessages = sessionRetrievalService.getSessionWithAllMessages(sessionId);

        SessionMessagesResponseDTO result;
        try {
            result = MappingUtils.sessionToSessionMessagesResponseDTO(sessionWithMessages);
        } catch (Exception e) {
            log.error("An error occurred while mapping the session with messages.");
            throw new RuntimeException("Failed to map the session with messages. Please check the logs for details.", e);
        }

        return result;
    }

}