package com.noname.lnasessionapi.util;

import com.noname.lnasessionapi.data.Session;
import com.noname.lnasessiondto.SessionResponseDTO;

public class MappingUtils {

    public static SessionResponseDTO sessionToSessionResponseDTO(Session session) {
        return SessionResponseDTO.builder().build();
    }
}
