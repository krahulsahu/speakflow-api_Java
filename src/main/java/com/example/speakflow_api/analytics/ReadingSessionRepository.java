package com.example.speakflow_api.analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingSessionRepository extends JpaRepository<ReadingSession, Long> {
    List<ReadingSession> findByDeviceIdOrderByCreatedAtDesc(String deviceId);
}
