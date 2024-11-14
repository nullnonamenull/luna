package com.noname.openaigateway.controller;


import com.noname.openaidto.OpenAIRequestDTO;
import com.noname.openaidto.OpenAIResponseDTO;
import com.noname.openaigateway.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final OpenAIService openAIService;

    @PostMapping("/chat")
    public ResponseEntity<OpenAIResponseDTO> getChatResponse(@RequestBody OpenAIRequestDTO requestDTO) {
        OpenAIResponseDTO response = openAIService.sendPrompt(requestDTO);
        return ResponseEntity.ok(response);
    }
}

