package com.smartprep.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Map;
import java.util.HashMap;

@Service
public class AiCommunicationService {

    private final RestTemplate restTemplate = new RestTemplate();

    @org.springframework.beans.factory.annotation.Value("${ai.service.url:http://localhost:8000}")
    private String FASTAPI_URL;

    public Map<String, Object> generateNotes(String extractedText) {
        String url = FASTAPI_URL + "/generate-notes";
        return postToFastAPI(url, extractedText);
    }

    public Map<String, Object> generateQuiz(String extractedText) {
        String url = FASTAPI_URL + "/generate-quiz";
        return postToFastAPI(url, extractedText);
    }
    
    public Map<String, Object> generateSchedule(Map<String, Object> scheduleReq) {
        String url = FASTAPI_URL + "/generate-schedule";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(scheduleReq, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return response.getBody();
    }

    private Map<String, Object> postToFastAPI(String url, String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("text", text);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return response.getBody();
    }
}
