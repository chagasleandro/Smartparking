package com.example.smartparking.controller;

import com.example.smartparking.dto.SensorEventDto;
import com.example.smartparking.entity.ParkingSpot;
import com.example.smartparking.entity.enums.SpotStatus;
import com.example.smartparking.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final ParkingSpotRepository spotRepo;
    private final SimpMessagingTemplate messaging;

    @PostMapping("/event")
    public ResponseEntity<?> ingest(@RequestBody SensorEventDto dto) {
        var spotOpt = spotRepo.findBySensorId(dto.getSensorId());
        if (spotOpt.isEmpty()) return ResponseEntity.notFound().build();
        var spot = spotOpt.get();
        if ("OCCUPIED".equalsIgnoreCase(dto.getEventType())) {
            spot.setStatus(SpotStatus.OCCUPIED);
        } else if ("FREE".equalsIgnoreCase(dto.getEventType())) {
            spot.setStatus(SpotStatus.FREE);
        }
        spot.setLastSeenAt(Instant.now());
        spotRepo.save(spot);
        messaging.convertAndSend("/topic/parking/" + spot.getId(), dto.getEventType());
        return ResponseEntity.ok().build();
    }
}
