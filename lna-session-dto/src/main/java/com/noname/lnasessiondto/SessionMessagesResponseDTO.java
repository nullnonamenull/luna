package com.noname.lnasessiondto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SessionMessagesResponseDTO {

    private UUID sessionId;
    private String sessionName;
    private List<MessageDTO> messages;
}
