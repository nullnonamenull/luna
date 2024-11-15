package com.noname.openaigateway.service;

import com.noname.openaidto.OpenAIRequestDTO;
import com.noname.openaidto.OpenAIResponseDTO;
import com.noname.openaidto.TranscriptionResponseDTO;
import com.noname.openaigateway.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final RestClient restClient;
    private final RestTemplate restTemplate;
    private final AppProperties appProperties;

    public OpenAIResponseDTO sendPrompt(OpenAIRequestDTO requestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(appProperties.getApiKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String payload = String.format(
                "{\"model\": \"%s\", \"messages\": [{\"role\": \"%s\", \"content\": \"%s\"}]}",
                requestDTO.getModel(), requestDTO.getRole(), requestDTO.getPrompt()
        );

        return sendRequest(appProperties.getCompletionsUrl(), headers, payload);
    }

    private OpenAIResponseDTO sendRequest(String uri, HttpHeaders headers, String payload) {
        try {
            return restClient
                    .post()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .body(payload)
                    .retrieve()
                    .body(OpenAIResponseDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Error communicating with OpenAI API: " + e.getMessage(), e);
        }
    }

    public TranscriptionResponseDTO transcribeAudio(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(appProperties.getApiKey());
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("model", "whisper-1");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<TranscriptionResponseDTO> response = restTemplate.postForEntity(
                    appProperties.getTranscriptionUrl(),
                    requestEntity,
                    TranscriptionResponseDTO.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with Whisper API: " + e.getMessage(), e);
        }
    }

}