package com.example.speakflow_api.ai;

import com.example.speakflow_api.reading.GeneratedParagraph;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final OpenAIService openAIService;

    public AIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/generate")
    public ResponseEntity<GeneratedParagraph> generateParagraph(@Valid @RequestBody ParagraphGenerateRequest request) {
        GeneratedParagraph paragraph = openAIService.generateParagraph(request);
        return ResponseEntity.ok(paragraph);
    }
}
