package com.example.speakflow_api.ai;

import jakarta.validation.constraints.NotBlank;

public class ParagraphGenerateRequest {
    @NotBlank
    private String topic;
    private String difficulty = "Intermediate"; // Beginner, Intermediate, Advanced
    private String length = "Medium"; // Short, Medium, Long
    private String tone = "Casual"; // Formal, Casual, Professional

    // Getters and Setters
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }

    public String getTone() { return tone; }
    public void setTone(String tone) { this.tone = tone; }
}
