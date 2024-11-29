package com.noname.lnasessionapi.controller;

import com.noname.lnasessionapi.service.SessionService;
import com.noname.lnasessiondto.SessionCreationRequestDTO;
import com.noname.lnasessiondto.SessionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/session/")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionResponseDTO> createSession(@RequestBody SessionCreationRequestDTO sessionCreationRequestDTO) {
        return ResponseEntity.ok(sessionService.createSession(sessionCreationRequestDTO));
    }

}
