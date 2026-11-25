package com.arosetu.backend.repository;

import com.arosetu.backend.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDoctorId(Long doctorId);
    List<Availability> findByDoctorIdAndHospitalId(Long doctorId, Long hospitalId);
    Optional<Availability> findByDoctorIdAndHospitalIdAndDayOfWeek(Long doctorId, Long hospitalId, DayOfWeek dayOfWeek);
}

