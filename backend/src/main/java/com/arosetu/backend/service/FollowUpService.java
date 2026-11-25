package com.arosetu.backend.service;

import com.arosetu.backend.dto.FollowUpRequest;
import com.arosetu.backend.entity.Consultation;
import com.arosetu.backend.entity.FollowUp;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.ConsultationRepository;
import com.arosetu.backend.repository.FollowUpRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FollowUpService {

    private final FollowUpRepository followUpRepository;
    private final ConsultationRepository consultationRepository;

    public FollowUpService(FollowUpRepository followUpRepository,
                           ConsultationRepository consultationRepository) {
        this.followUpRepository = followUpRepository;
        this.consultationRepository = consultationRepository;
    }

    @Transactional
    public FollowUp createFollowUp(Long doctorId, FollowUpRequest request) {
        Consultation consultation = consultationRepository.findById(request.getConsultationId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        FollowUp followUp = new FollowUp();
        followUp.setConsultation(consultation);
        followUp.setDoctor(consultation.getDoctor());
        followUp.setPatient(consultation.getPatient());
        followUp.setFollowUpDate(request.getFollowUpDate());
        followUp.setReason(request.getReason());
        followUp.setInstructions(request.getInstructions());

        return followUpRepository.save(followUp);
    }

    public List<FollowUp> getFollowUpsByPatient(Long patientId) {
        return followUpRepository.findByPatientId(patientId);
    }

    public List<FollowUp> getFollowUpsByDoctor(Long doctorId) {
        return followUpRepository.findByDoctorId(doctorId);
    }

    public List<FollowUp> getUpcomingFollowUps(Long patientId) {
        return followUpRepository.findByPatientIdAndFollowUpDateGreaterThanEqual(patientId, LocalDate.now());
    }
}

