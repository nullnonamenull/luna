package com.noname.openaigateway.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noname.openaidto.*;
import com.noname.openaigateway.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final RestTemplate restTemplate;
    private final AppProperties appProperties;


    public OpenAIResponseDTO sendPrompt(final OpenAIRequestDTO requestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(appProperties.getApiKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String payload = objectMapper.writeValueAsString(requestDTO);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    appProperties.getCompletionsUrl(),
                    new HttpEntity<>(payload, headers),
                    String.class
            );

            JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
            String content = rootNode.path("choices").get(0).path("message").path("content").asText();

            String formattedContent = StringEscapeUtils.unescapeJava(content);

            OpenAIResponseDTO simpleResponse = new OpenAIResponseDTO();
            simpleResponse.setText(formattedContent);
            return simpleResponse;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("OpenAI API error: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with OpenAI API: " + e.getMessage(), e);
        }
    }

    public TranscriptionResponseDTO transcribeAudio(MultipartFile file, String model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(appProperties.getApiKey());
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("model", model);

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

    public ImageDescriptionResponseDTO describeImageMultipart(MultipartFile file, String model, String question, int maxTokens) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(appProperties.getApiKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String base64Image = encodeFileToBase64(file);
        List<Map<String, Object>> messages = getMaps(question, base64Image);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("messages", messages);
        requestBody.put("max_tokens", maxTokens);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> rawResponse = restTemplate.postForEntity(
                    appProperties.getCompletionsUrl(),
                    requestEntity,
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(rawResponse.getBody());
            String content = rootNode.path("choices").get(0).path("message").path("content").asText();

            ImageDescriptionResponseDTO responseDTO = new ImageDescriptionResponseDTO();
            responseDTO.setText(content);
            return responseDTO;

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("OpenAI API error: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with Image Description API: " + e.getMessage(), e);
        }
    }

    public OpenAIVectorResponseDTO generateVector(OpenAIVectorRequestDTO requestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(appProperties.getApiKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String payload = String.format(
                "{\"model\": \"%s\", \"input\": \"%s\"}",
                requestDTO.getModel(),
                requestDTO.getInput()
        );

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    appProperties.getEmbeddingsUrl(),
                    new HttpEntity<>(payload, headers),
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
            JsonNode embeddingNode = rootNode.path("data").get(0).path("embedding");

            List<Float> vector = objectMapper.convertValue(embeddingNode, new TypeReference<>() {
            });

            return new OpenAIVectorResponseDTO(vector, requestDTO.getModel(), requestDTO.getInput());

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("OpenAI API error: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with OpenAI API: " + e.getMessage(), e);
        }
    }


    private String encodeFileToBase64(MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException("Error encoding file to Base64: " + e.getMessage(), e);
        }
    }

    private static List<Map<String, Object>> getMaps(String question, String base64Image) {
        String dataUrl = "data:image/jpeg;base64," + base64Image;

        Map<String, Object> imageUrlMap = new HashMap<>();
        imageUrlMap.put("url", dataUrl);

        Map<String, Object> imageMessage = new HashMap<>();
        imageMessage.put("type", "image_url");
        imageMessage.put("image_url", imageUrlMap);

        Map<String, Object> textMessage = new HashMap<>();
        textMessage.put("type", "text");
        textMessage.put("text", question);

        List<Map<String, Object>> contentList = new ArrayList<>();
        contentList.add(textMessage);
        contentList.add(imageMessage);

        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", contentList);

        List<Map<String, Object>> messages = new ArrayList<>();
        messages.add(userMessage);
        return messages;
    }

}