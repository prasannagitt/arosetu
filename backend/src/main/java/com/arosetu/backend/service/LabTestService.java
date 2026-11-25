package com.arosetu.backend.service;

import com.arosetu.backend.dto.LabTestRequestDto;
import com.arosetu.backend.entity.LabTestRequest;
import com.arosetu.backend.enums.LabTestStatus;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LabTestService {

    private final LabTestRequestRepository labTestRequestRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final ConsultationRepository consultationRepository;

    public LabTestService(LabTestRequestRepository labTestRequestRepository,
                          DoctorRepository doctorRepository,
                          PatientRepository patientRepository,
                          LaboratoryRepository laboratoryRepository,
                          ConsultationRepository consultationRepository) {
        this.labTestRequestRepository = labTestRequestRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.laboratoryRepository = laboratoryRepository;
        this.consultationRepository = consultationRepository;
    }

    @Transactional
    public LabTestRequest createLabTest(Long doctorId, LabTestRequestDto request) {
        var doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        var laboratory = laboratoryRepository.findById(request.getLaboratoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Laboratory not found"));

        LabTestRequest labTest = new LabTestRequest();
        labTest.setDoctor(doctor);
        labTest.setLaboratory(laboratory);
        labTest.setTestName(request.getTestName());
        labTest.setInstructions(request.getInstructions());
        labTest.setStatus(LabTestStatus.REQUESTED);

        if (request.getConsultationId() != null) {
            var consultation = consultationRepository.findById(request.getConsultationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));
            labTest.setConsultation(consultation);
            labTest.setPatient(consultation.getPatient());
        }

        return labTestRequestRepository.save(labTest);
    }

    public LabTestRequest getLabTestById(Long id) {
        return labTestRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lab test not found"));
    }

    public List<LabTestRequest> getLabTestsByPatient(Long patientId) {
        return labTestRequestRepository.findByPatientId(patientId);
    }

    public List<LabTestRequest> getLabTestsByDoctor(Long doctorId) {
        return labTestRequestRepository.findByDoctorId(doctorId);
    }

    public List<LabTestRequest> getLabTestsByLaboratory(Long laboratoryId) {
        return labTestRequestRepository.findByLaboratoryId(laboratoryId);
    }

    @Transactional
    public LabTestRequest acceptTest(Long id) {
        LabTestRequest test = getLabTestById(id);
        if (test.getStatus() != LabTestStatus.REQUESTED) {
            throw new IllegalStateException("Can only accept REQUESTED tests");
        }
        test.setStatus(LabTestStatus.ACCEPTED);
        return labTestRequestRepository.save(test);
    }

    @Transactional
    public LabTestRequest collectSample(Long id) {
        LabTestRequest test = getLabTestById(id);
        if (test.getStatus() != LabTestStatus.ACCEPTED) {
            throw new IllegalStateException("Can only collect sample for ACCEPTED tests");
        }
        test.setStatus(LabTestStatus.SAMPLE_COLLECTED);
        return labTestRequestRepository.save(test);
    }

    @Transactional
    public LabTestRequest startProcessing(Long id) {
        LabTestRequest test = getLabTestById(id);
        if (test.getStatus() != LabTestStatus.SAMPLE_COLLECTED) {
            throw new IllegalStateException("Can only process SAMPLE_COLLECTED tests");
        }
        test.setStatus(LabTestStatus.PROCESSING);
        return labTestRequestRepository.save(test);
    }

    @Transactional
    public LabTestRequest completeTest(Long id, String resultSummary, String reportFileUrl) {
        LabTestRequest test = getLabTestById(id);
        if (test.getStatus() != LabTestStatus.PROCESSING) {
            throw new IllegalStateException("Can only complete PROCESSING tests");
        }
        test.setStatus(LabTestStatus.COMPLETED);
        test.setResultSummary(resultSummary);
        test.setReportFileUrl(reportFileUrl);
        test.setCompletedAt(LocalDateTime.now());
        return labTestRequestRepository.save(test);
    }

    @Transactional
    public LabTestRequest cancelTest(Long id) {
        LabTestRequest test = getLabTestById(id);
        if (test.getStatus() == LabTestStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel completed tests");
        }
        test.setStatus(LabTestStatus.CANCELLED);
        return labTestRequestRepository.save(test);
    }
}

