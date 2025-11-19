package com.example.smartparking.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Enumerated(EnumType.STRING)
    private SpotType type;
    @Enumerated(EnumType.STRING)
    private SpotStatus status = SpotStatus.FREE;
    private String sensorId;
    private Instant lastSeenAt;

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public SpotType getType() { return type; }
    public void setType(SpotType type) { this.type = type; }
    public SpotStatus getStatus() { return status; }
    public void setStatus(SpotStatus status) { this.status = status; }
    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    public Instant getLastSeenAt() { return lastSeenAt; }
    public void setLastSeenAt(Instant lastSeenAt) { this.lastSeenAt = lastSeenAt; }
}
