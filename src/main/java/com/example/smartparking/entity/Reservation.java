package com.example.smartparking.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ParkingSpot spot;
    private Long userId;
    private Instant startTime;
    private Instant endTime;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ParkingSpot getSpot() { return spot; }
    public void setSpot(ParkingSpot spot) { this.spot = spot; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Instant getStartTime() { return startTime; }
    public void setStartTime(Instant startTime) { this.startTime = startTime; }
    public Instant getEndTime() { return endTime; }
    public void setEndTime(Instant endTime) { this.endTime = endTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
