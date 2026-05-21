package com.example.speakflow_api.settings;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    private final AppSettingRepository repository;

    public SettingsController(AppSettingRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<AppSetting> saveSettings(@Valid @RequestBody AppSetting setting) {
        Optional<AppSetting> existing = repository.findByDeviceId(setting.getDeviceId());
        if (existing.isPresent()) {
            AppSetting toUpdate = existing.get();
            toUpdate.setDarkMode(setting.isDarkMode());
            toUpdate.setDefaultFontSize(setting.getDefaultFontSize());
            toUpdate.setDefaultScrollSpeed(setting.getDefaultScrollSpeed());
            toUpdate.setPreferredTheme(setting.getPreferredTheme());
            return ResponseEntity.ok(repository.save(toUpdate));
        }
        return ResponseEntity.ok(repository.save(setting));
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<AppSetting> getSettings(@PathVariable String deviceId) {
        return repository.findByDeviceId(deviceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
