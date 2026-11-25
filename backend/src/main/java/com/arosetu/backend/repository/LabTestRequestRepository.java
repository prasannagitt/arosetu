package com.arosetu.backend.repository;

import com.arosetu.backend.entity.LabTestRequest;
import com.arosetu.backend.enums.LabTestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabTestRequestRepository extends JpaRepository<LabTestRequest, Long> {
    List<LabTestRequest> findByPatientId(Long patientId);
    List<LabTestRequest> findByDoctorId(Long doctorId);
    List<LabTestRequest> findByLaboratoryId(Long laboratoryId);
    List<LabTestRequest> findByLaboratoryIdAndStatusIn(Long laboratoryId, List<LabTestStatus> statuses);
}

