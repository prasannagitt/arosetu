package com.arosetu.backend.service;

import com.arosetu.backend.dto.PatientResponse;
import com.arosetu.backend.entity.Patient;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient getPatientEntityById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public Patient getPatientByUserId(Long userId) {
        return patientRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient profile not found for user id: " + userId));
    }

    @Transactional
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setBloodGroup(updatedPatient.getBloodGroup());
        existingPatient.setAddress(updatedPatient.getAddress());
        existingPatient.setEmergencyContact(updatedPatient.getEmergencyContact());
        return patientRepository.save(existingPatient);
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    public PatientResponse mapToResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getUser().getName(),
                patient.getUser().getEmail(),
                patient.getUser().getPhone(),
                patient.getDateOfBirth(),
                patient.getGender(),
                patient.getBloodGroup(),
                patient.getAddress(),
                patient.getEmergencyContact()
        );
    }
}
