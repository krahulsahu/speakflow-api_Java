package com.example.speakflow_api.ai;

import com.example.speakflow_api.reading.GeneratedParagraph;
import com.example.speakflow_api.reading.GeneratedParagraphRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String openAiKey;

    private final RestTemplate restTemplate;
    private final GeneratedParagraphRepository paragraphRepository;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    public OpenAIService(RestTemplate restTemplate, GeneratedParagraphRepository paragraphRepository) {
        this.restTemplate = restTemplate;
        this.paragraphRepository = paragraphRepository;
    }

    public GeneratedParagraph generateParagraph(ParagraphGenerateRequest request) {
        String prompt = buildPrompt(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o-mini"); // Using a fast, cost-effective model

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are an English language tutor creating reading practice paragraphs.");
        
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        messages.add(systemMessage);
        messages.add(userMessage);

        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 500);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            Map<String, Object> response = restTemplate.postForObject(OPENAI_URL, entity, Map.class);
            
            if (response != null && response.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                String content = (String) message.get("content");

                // Save to Database
                GeneratedParagraph paragraph = new GeneratedParagraph();
                paragraph.setTopic(request.getTopic());
                paragraph.setDifficulty(request.getDifficulty());
                paragraph.setTone(request.getTone());
                paragraph.setContent(content.trim());
                
                return paragraphRepository.save(paragraph);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate AI paragraph: " + e.getMessage());
        }

        throw new RuntimeException("Failed to generate AI paragraph: Empty response");
    }

    private String buildPrompt(ParagraphGenerateRequest req) {
        return String.format(
            "Write a %s %s paragraph about '%s'. The tone should be %s. " +
            "The text is meant for non-native speakers to practice reading aloud. " +
            "Do not include any greetings or explanations, just output the paragraph text.",
            req.getLength(), req.getDifficulty(), req.getTopic(), req.getTone()
        );
    }
}
