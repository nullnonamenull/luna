package com.noname.lnasessiondto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SessionResponseDTO {

    private UUID sessionId;
    private String sessionName;

}