package com.arosetu.backend.service;

import com.arosetu.backend.dto.ConsultationRequest;
import com.arosetu.backend.entity.Appointment;
import com.arosetu.backend.entity.Consultation;
import com.arosetu.backend.enums.AppointmentStatus;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.AppointmentRepository;
import com.arosetu.backend.repository.ConsultationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final AppointmentRepository appointmentRepository;

    public ConsultationService(ConsultationRepository consultationRepository,
                               AppointmentRepository appointmentRepository) {
        this.consultationRepository = consultationRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public Consultation createConsultation(Long doctorId, ConsultationRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
            throw new IllegalStateException("Appointment must be COMPLETED before creating a consultation");
        }

        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new IllegalStateException("This appointment does not belong to you");
        }

        if (consultationRepository.findByAppointmentId(appointment.getId()).isPresent()) {
            throw new IllegalStateException("Consultation already exists for this appointment");
        }

        Consultation consultation = new Consultation();
        consultation.setAppointment(appointment);
        consultation.setPatient(appointment.getPatient());
        consultation.setDoctor(appointment.getDoctor());
        consultation.setSymptoms(request.getSymptoms());
        consultation.setObservations(request.getObservations());
        consultation.setDiagnosis(request.getDiagnosis());
        consultation.setClinicalNotes(request.getClinicalNotes());
        consultation.setTreatmentPlan(request.getTreatmentPlan());
        consultation.setConsultationDate(LocalDateTime.now());

        return consultationRepository.save(consultation);
    }

    public Consultation getConsultationById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));
    }

    public List<Consultation> getConsultationsByPatient(Long patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    public List<Consultation> getConsultationsByDoctor(Long doctorId) {
        return consultationRepository.findByDoctorId(doctorId);
    }

    @Transactional
    public Consultation updateConsultation(Long id, ConsultationRequest request) {
        Consultation existing = getConsultationById(id);
        if (request.getSymptoms() != null) existing.setSymptoms(request.getSymptoms());
        if (request.getObservations() != null) existing.setObservations(request.getObservations());
        if (request.getDiagnosis() != null) existing.setDiagnosis(request.getDiagnosis());
        if (request.getClinicalNotes() != null) existing.setClinicalNotes(request.getClinicalNotes());
        if (request.getTreatmentPlan() != null) existing.setTreatmentPlan(request.getTreatmentPlan());
        return consultationRepository.save(existing);
    }
}

