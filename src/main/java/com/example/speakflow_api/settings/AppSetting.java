package com.example.speakflow_api.settings;

import jakarta.persistence.*;

@Entity
@Table(name = "app_settings")
public class AppSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String deviceId; // Anonymous user identifier

    private boolean darkMode = false;
    private int defaultFontSize = 48;
    private double defaultScrollSpeed = 1.0;
    private String preferredTheme = "modern"; // modern, classic, minimal

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public boolean isDarkMode() { return darkMode; }
    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }

    public int getDefaultFontSize() { return defaultFontSize; }
    public void setDefaultFontSize(int defaultFontSize) { this.defaultFontSize = defaultFontSize; }

    public double getDefaultScrollSpeed() { return defaultScrollSpeed; }
    public void setDefaultScrollSpeed(double defaultScrollSpeed) { this.defaultScrollSpeed = defaultScrollSpeed; }

    public String getPreferredTheme() { return preferredTheme; }
    public void setPreferredTheme(String preferredTheme) { this.preferredTheme = preferredTheme; }
}
