package com.noname.openaigateway.controller;


import com.noname.openaidto.ImageDescriptionResponseDTO;
import com.noname.openaidto.OpenAIRequestDTO;
import com.noname.openaidto.OpenAIResponseDTO;
import com.noname.openaidto.TranscriptionResponseDTO;
import com.noname.openaigateway.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/transcription", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TranscriptionResponseDTO> transcribeAudio(@RequestParam("audio") MultipartFile audio) {
        TranscriptionResponseDTO response = openAIService.transcribeAudio(audio);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/image-description", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageDescriptionResponseDTO> describeImage(@RequestParam("image") MultipartFile image) {
        ImageDescriptionResponseDTO response = openAIService.describeImage(image);
        return ResponseEntity.ok(response);
    }

}