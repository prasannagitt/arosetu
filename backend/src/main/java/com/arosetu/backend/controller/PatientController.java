package com.arosetu.backend.controller;

import com.arosetu.backend.dto.PatientProfileRequest;
import com.arosetu.backend.dto.PatientResponse;
import com.arosetu.backend.entity.Patient;
import com.arosetu.backend.entity.User;
import com.arosetu.backend.service.PatientService;
import com.arosetu.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;

    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientResponse> getMyProfile(Authentication auth) {
        User user = userService.getUserEntityByEmail(auth.getName());
        Patient patient = patientService.getPatientByUserId(user.getId());
        return ResponseEntity.ok(patientService.mapToResponse(patient));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientResponse> updateMyProfile(Authentication auth, @RequestBody PatientProfileRequest request) {
        User user = userService.getUserEntityByEmail(auth.getName());
        Patient patient = patientService.getPatientByUserId(user.getId());

        Patient updateData = new Patient();
        updateData.setDateOfBirth(request.getDateOfBirth());
        updateData.setGender(request.getGender());
        updateData.setBloodGroup(request.getBloodGroup());
        updateData.setAddress(request.getAddress());
        updateData.setEmergencyContact(request.getEmergencyContact());

        Patient updated = patientService.updatePatient(patient.getId(), updateData);
        return ResponseEntity.ok(patientService.mapToResponse(updated));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('DOCTOR','ADMIN')")
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> patients = patientService.getAllPatients().stream()
                .map(patientService::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR','ADMIN')")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientEntityById(id);
        return ResponseEntity.ok(patientService.mapToResponse(patient));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
