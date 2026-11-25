package com.arosetu.backend.service;

import com.arosetu.backend.dto.PrescriptionRequest;
import com.arosetu.backend.entity.Consultation;
import com.arosetu.backend.entity.Pharmacy;
import com.arosetu.backend.entity.Prescription;
import com.arosetu.backend.entity.PrescriptionItem;
import com.arosetu.backend.enums.PrescriptionStatus;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.ConsultationRepository;
import com.arosetu.backend.repository.PharmacyRepository;
import com.arosetu.backend.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final ConsultationRepository consultationRepository;
    private final PharmacyRepository pharmacyRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               ConsultationRepository consultationRepository,
                               PharmacyRepository pharmacyRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationRepository = consultationRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    @Transactional
    public Prescription createPrescription(Long doctorId, PrescriptionRequest request) {
        Consultation consultation = consultationRepository.findById(request.getConsultationId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        if (!consultation.getDoctor().getId().equals(doctorId)) {
            throw new IllegalStateException("This consultation does not belong to you");
        }

        Prescription prescription = new Prescription();
        prescription.setConsultation(consultation);
        prescription.setDoctor(consultation.getDoctor());
        prescription.setPatient(consultation.getPatient());
        prescription.setStatus(PrescriptionStatus.DRAFT);
        prescription.setNotes(request.getNotes());

        List<PrescriptionItem> items = request.getItems().stream().map(itemReq -> {
            PrescriptionItem item = new PrescriptionItem();
            item.setPrescription(prescription);
            item.setMedicineName(itemReq.getMedicineName());
            item.setDosage(itemReq.getDosage());
            item.setFrequency(itemReq.getFrequency());
            item.setDuration(itemReq.getDuration());
            item.setInstructions(itemReq.getInstructions());
            return item;
        }).collect(Collectors.toList());

        prescription.setItems(items);
        return prescriptionRepository.save(prescription);
    }

    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found"));
    }

    public List<Prescription> getPrescriptionsByPatient(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    public List<Prescription> getPrescriptionsByDoctor(Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }

    public List<Prescription> getPrescriptionsByPharmacy(Long pharmacyId) {
        return prescriptionRepository.findByPharmacyIdAndStatusIn(pharmacyId,
                List.of(PrescriptionStatus.SENT_TO_PHARMACY, PrescriptionStatus.READY, PrescriptionStatus.DISPENSED));
    }

    @Transactional
    public Prescription prescribe(Long id) {
        Prescription prescription = getPrescriptionById(id);
        if (prescription.getStatus() != PrescriptionStatus.DRAFT) {
            throw new IllegalStateException("Can only prescribe DRAFT prescriptions");
        }
        prescription.setStatus(PrescriptionStatus.PRESCRIBED);
        return prescriptionRepository.save(prescription);
    }

    @Transactional
    public Prescription sendToPharmacy(Long id, Long pharmacyId) {
        Prescription prescription = getPrescriptionById(id);
        if (prescription.getStatus() != PrescriptionStatus.PRESCRIBED) {
            throw new IllegalStateException("Can only send PRESCRIBED prescriptions to pharmacy");
        }
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found"));
        prescription.setPharmacy(pharmacy);
        prescription.setStatus(PrescriptionStatus.SENT_TO_PHARMACY);
        return prescriptionRepository.save(prescription);
    }

    @Transactional
    public Prescription markReady(Long id) {
        Prescription prescription = getPrescriptionById(id);
        if (prescription.getStatus() != PrescriptionStatus.SENT_TO_PHARMACY) {
            throw new IllegalStateException("Can only mark SENT_TO_PHARMACY prescriptions as ready");
        }
        prescription.setStatus(PrescriptionStatus.READY);
        return prescriptionRepository.save(prescription);
    }

    @Transactional
    public Prescription markDispensed(Long id) {
        Prescription prescription = getPrescriptionById(id);
        if (prescription.getStatus() != PrescriptionStatus.READY) {
            throw new IllegalStateException("Can only dispense READY prescriptions");
        }
        prescription.setStatus(PrescriptionStatus.DISPENSED);
        return prescriptionRepository.save(prescription);
    }
}

