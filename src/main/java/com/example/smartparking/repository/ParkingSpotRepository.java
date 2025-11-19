package com.example.smartparking.repository;

import com.example.smartparking.entity.ParkingSpot;
import com.example.smartparking.entity.enums.SpotStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByStatus(SpotStatus status);
    Optional<ParkingSpot> findBySensorId(String sensorId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ParkingSpot p where p.id = :id")
    Optional<ParkingSpot> findByIdForUpdate(@Param("id") Long id);
}
