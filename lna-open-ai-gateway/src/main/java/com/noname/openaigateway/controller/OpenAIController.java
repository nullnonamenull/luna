package com.noname.openaigateway.controller;


import com.noname.openaidto.*;
import com.noname.openaigateway.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
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

    @PostMapping(value = "/transcribe-audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TranscriptionResponseDTO> transcribeAudio(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "model", defaultValue = "whisper-1") String model
    ) {
        TranscriptionResponseDTO response = openAIService.transcribeAudio(file, model);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/image-description", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageDescriptionResponseDTO> describeImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("model") String model,
            @RequestParam("question") String question,
            @RequestParam(value = "max_tokens", defaultValue = "300") int maxTokens
    ) {
        ImageDescriptionResponseDTO response = openAIService.describeImageMultipart(image, model, question, maxTokens);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/generate-vector", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OpenAIVectorResponseDTO> generateVector(@RequestBody OpenAIVectorRequestDTO requestDTO) {
        OpenAIVectorResponseDTO response = openAIService.generateVector(requestDTO);
        return ResponseEntity.ok(response);
    }


}