package com.noname.lnasessionapi.service;

import com.noname.lnasessionapi.repository.SessionRepository;
import com.noname.lnasessiondto.SessionCreationRequestDTO;
import com.noname.lnasessiondto.SessionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;



    public SessionResponseDTO getSession(String userId) {
        return null;
    }


    public SessionResponseDTO createSession(SessionCreationRequestDTO sessionCreationRequestDTO) {
        return null;
    }
}