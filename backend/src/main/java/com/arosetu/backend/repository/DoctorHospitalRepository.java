package com.arosetu.backend.repository;

import com.arosetu.backend.entity.DoctorHospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorHospitalRepository extends JpaRepository<DoctorHospital, Long> {
    List<DoctorHospital> findByDoctorId(Long doctorId);
    List<DoctorHospital> findByHospitalId(Long hospitalId);
    Optional<DoctorHospital> findByDoctorIdAndHospitalId(Long doctorId, Long hospitalId);
}

