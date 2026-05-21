package com.example.speakflow_api.analytics;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reading_sessions")
public class ReadingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deviceId; // Anonymous user identifier

    private String topic;
    private int wpm;
    private int accuracy;
    private int wordsRead;
    private int durationSeconds;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public int getWpm() { return wpm; }
    public void setWpm(int wpm) { this.wpm = wpm; }

    public int getAccuracy() { return accuracy; }
    public void setAccuracy(int accuracy) { this.accuracy = accuracy; }

    public int getWordsRead() { return wordsRead; }
    public void setWordsRead(int wordsRead) { this.wordsRead = wordsRead; }

    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
