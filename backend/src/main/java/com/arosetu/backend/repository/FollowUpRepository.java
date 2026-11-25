package com.arosetu.backend.repository;

import com.arosetu.backend.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {
    List<FollowUp> findByPatientId(Long patientId);
    List<FollowUp> findByDoctorId(Long doctorId);
    List<FollowUp> findByFollowUpDateBetween(LocalDate start, LocalDate end);
    List<FollowUp> findByPatientIdAndFollowUpDateGreaterThanEqual(Long patientId, LocalDate date);
}

