package com.arosetu.backend.repository;

import com.arosetu.backend.entity.Prescription;
import com.arosetu.backend.enums.PrescriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatientId(Long patientId);
    List<Prescription> findByDoctorId(Long doctorId);
    List<Prescription> findByPharmacyIdAndStatusIn(Long pharmacyId, List<PrescriptionStatus> statuses);
    List<Prescription> findByConsultationId(Long consultationId);
}

