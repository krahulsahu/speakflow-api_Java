package com.example.speakflow_api.analytics;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final ReadingSessionRepository repository;

    public AnalyticsController(ReadingSessionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/session")
    public ResponseEntity<ReadingSession> saveSession(@Valid @RequestBody ReadingSession session) {
        ReadingSession saved = repository.save(session);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/sessions/{deviceId}")
    public ResponseEntity<List<ReadingSession>> getSessions(@PathVariable String deviceId) {
        List<ReadingSession> sessions = repository.findByDeviceIdOrderByCreatedAtDesc(deviceId);
        return ResponseEntity.ok(sessions);
    }
}
