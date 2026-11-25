package com.arosetu.backend.controller;

import com.arosetu.backend.dto.ConsultationRequest;
import com.arosetu.backend.dto.ConsultationResponse;
import com.arosetu.backend.entity.Consultation;
import com.arosetu.backend.entity.Doctor;
import com.arosetu.backend.entity.Patient;
import com.arosetu.backend.entity.User;
import com.arosetu.backend.enums.Role;
import com.arosetu.backend.service.ConsultationService;
import com.arosetu.backend.service.DoctorService;
import com.arosetu.backend.service.PatientService;
import com.arosetu.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    private final ConsultationService consultationService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public ConsultationController(ConsultationService consultationService, UserService userService,
                                   DoctorService doctorService, PatientService patientService) {
        this.consultationService = consultationService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ConsultationResponse> create(@Valid @RequestBody ConsultationRequest request,
                                                        Authentication auth) {
        Doctor doctor = getDoctorFromAuth(auth);
        Consultation c = consultationService.createConsultation(doctor.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(consultationService.getConsultationById(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<List<ConsultationResponse>> getMy(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        List<Consultation> list;
        if (user.getRole() == Role.PATIENT) {
            Patient patient = patientService.getPatientByUserId(user.getId());
            list = consultationService.getConsultationsByPatient(patient.getId());
        } else {
            Doctor doctor = doctorService.getDoctorByUserId(user.getId());
            list = consultationService.getConsultationsByDoctor(doctor.getId());
        }
        return ResponseEntity.ok(list.stream().map(this::mapToResponse).toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ConsultationResponse> update(@PathVariable Long id,
                                                        @RequestBody ConsultationRequest request) {
        return ResponseEntity.ok(mapToResponse(consultationService.updateConsultation(id, request)));
    }

    private Doctor getDoctorFromAuth(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return doctorService.getDoctorByUserId(user.getId());
    }

    private ConsultationResponse mapToResponse(Consultation c) {
        return new ConsultationResponse(c.getId(), c.getAppointment().getId(),
                c.getPatient().getId(), c.getPatient().getUser().getName(),
                c.getDoctor().getId(), c.getDoctor().getUser().getName(),
                c.getSymptoms(), c.getObservations(), c.getDiagnosis(),
                c.getClinicalNotes(), c.getTreatmentPlan(), c.getConsultationDate());
    }
}
