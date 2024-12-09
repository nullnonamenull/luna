package com.noname.lnasessionapi.util;

import com.noname.lnasessionapi.data.Message;
import com.noname.lnasessionapi.data.Session;
import com.noname.lnasessiondto.MessageDTO;
import com.noname.lnasessiondto.SessionMessagesResponseDTO;
import com.noname.lnasessiondto.SessionResponseDTO;
import lombok.NonNull;

public class MappingUtils {

    @NonNull
    public static SessionResponseDTO sessionToSessionResponseDTO(final Session session) {
        return SessionResponseDTO.builder()
                .sessionId(session.getId())
                .sessionName(session.getSessionName())
                .build();
    }

    @NonNull
    public static SessionMessagesResponseDTO sessionToSessionMessagesResponseDTO(final Session sessionWithMessages) {
        return SessionMessagesResponseDTO.builder()
                .sessionId(sessionWithMessages.getId())
                .sessionName(sessionWithMessages.getSessionName())
                .messages(sessionWithMessages.getMessages()
                        .stream()
                        .map(MappingUtils::messageToMessageDTO)
                        .toList())
                .build();
    }

    @NonNull
    public static MessageDTO messageToMessageDTO(final Message message) {
        return MessageDTO.builder()
                .content(message.getContent())
                .sender(message.getRole().name())
                .build();
    }

}