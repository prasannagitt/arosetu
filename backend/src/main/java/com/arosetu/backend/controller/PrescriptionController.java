package com.arosetu.backend.controller;

import com.arosetu.backend.dto.*;
import com.arosetu.backend.entity.*;
import com.arosetu.backend.enums.Role;
import com.arosetu.backend.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public PrescriptionController(PrescriptionService prescriptionService, UserService userService,
                                   DoctorService doctorService, PatientService patientService) {
        this.prescriptionService = prescriptionService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<PrescriptionResponse> create(@Valid @RequestBody PrescriptionRequest request,
                                                        Authentication auth) {
        Doctor doctor = getDoctorFromAuth(auth);
        Prescription p = prescriptionService.createPrescription(doctor.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(prescriptionService.getPrescriptionById(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<List<PrescriptionResponse>> getMy(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        List<Prescription> list;
        if (user.getRole() == Role.PATIENT) {
            Patient patient = patientService.getPatientByUserId(user.getId());
            list = prescriptionService.getPrescriptionsByPatient(patient.getId());
        } else {
            Doctor doctor = doctorService.getDoctorByUserId(user.getId());
            list = prescriptionService.getPrescriptionsByDoctor(doctor.getId());
        }
        return ResponseEntity.ok(list.stream().map(this::mapToResponse).toList());
    }

    @GetMapping("/pharmacy/{pharmacyId}")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<PrescriptionResponse>> getByPharmacy(@PathVariable Long pharmacyId) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByPharmacy(pharmacyId)
                .stream().map(this::mapToResponse).toList());
    }

    @PatchMapping("/{id}/prescribe")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<PrescriptionResponse> prescribe(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(prescriptionService.prescribe(id)));
    }

    @PatchMapping("/{id}/send-to-pharmacy")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<PrescriptionResponse> sendToPharmacy(@PathVariable Long id,
                                                                 @RequestParam Long pharmacyId) {
        return ResponseEntity.ok(mapToResponse(prescriptionService.sendToPharmacy(id, pharmacyId)));
    }

    @PatchMapping("/{id}/ready")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<PrescriptionResponse> markReady(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(prescriptionService.markReady(id)));
    }

    @PatchMapping("/{id}/dispense")
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<PrescriptionResponse> markDispensed(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(prescriptionService.markDispensed(id)));
    }

    private Doctor getDoctorFromAuth(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return doctorService.getDoctorByUserId(user.getId());
    }

    private PrescriptionResponse mapToResponse(Prescription p) {
        List<PrescriptionItemResponse> items = p.getItems() != null ? p.getItems().stream()
                .map(i -> new PrescriptionItemResponse(i.getId(), i.getMedicineName(),
                        i.getDosage(), i.getFrequency(), i.getDuration(), i.getInstructions()))
                .toList() : List.of();

        return new PrescriptionResponse(p.getId(), p.getConsultation().getId(),
                p.getDoctor().getId(), p.getDoctor().getUser().getName(),
                p.getPatient().getId(), p.getPatient().getUser().getName(),
                p.getPharmacy() != null ? p.getPharmacy().getId() : null,
                p.getPharmacy() != null ? p.getPharmacy().getName() : null,
                p.getStatus(), p.getNotes(), items, p.getCreatedAt());
    }
}
