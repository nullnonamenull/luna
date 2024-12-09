package com.noname.lnasessionapi.controller;

import com.noname.lnasessionapi.service.SessionService;
import com.noname.lnasessiondto.SessionMessagesResponseDTO;
import com.noname.lnasessiondto.SessionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/session/")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionResponseDTO> createSession() {
        return ResponseEntity.ok(sessionService.createSession());
    }

    @GetMapping
    public ResponseEntity<List<SessionResponseDTO>> getSessionResponseDTOList() {
        return ResponseEntity.ok(sessionService.getSessionResponseDTOList());
    }

    @GetMapping("/{sessionUid}/message")
    public ResponseEntity<SessionMessagesResponseDTO> getSessionMessagesResponseDTO(@PathVariable("sessionUid") final UUID sessionId) {
        return ResponseEntity.ok(sessionService.getSessionMessagesResponseDTO(sessionId));
    }

}