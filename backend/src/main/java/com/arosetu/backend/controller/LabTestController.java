package com.arosetu.backend.controller;

import com.arosetu.backend.dto.LabTestRequestDto;
import com.arosetu.backend.dto.LabTestResponse;
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
@RequestMapping("/api/lab-tests")
public class LabTestController {

    private final LabTestService labTestService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public LabTestController(LabTestService labTestService, UserService userService,
                              DoctorService doctorService, PatientService patientService) {
        this.labTestService = labTestService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<LabTestResponse> create(@Valid @RequestBody LabTestRequestDto request,
                                                    Authentication auth) {
        Doctor doctor = getDoctorFromAuth(auth);
        LabTestRequest t = labTestService.createLabTest(doctor.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(t));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabTestResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(labTestService.getLabTestById(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<List<LabTestResponse>> getMy(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        List<LabTestRequest> list;
        if (user.getRole() == Role.PATIENT) {
            Patient patient = patientService.getPatientByUserId(user.getId());
            list = labTestService.getLabTestsByPatient(patient.getId());
        } else {
            Doctor doctor = doctorService.getDoctorByUserId(user.getId());
            list = labTestService.getLabTestsByDoctor(doctor.getId());
        }
        return ResponseEntity.ok(list.stream().map(this::mapToResponse).toList());
    }

    @GetMapping("/laboratory/{labId}")
    @PreAuthorize("hasRole('LAB_TECHNICIAN')")
    public ResponseEntity<List<LabTestResponse>> getByLab(@PathVariable Long labId) {
        return ResponseEntity.ok(labTestService.getLabTestsByLaboratory(labId)
                .stream().map(this::mapToResponse).toList());
    }

    @PatchMapping("/{id}/accept")
    @PreAuthorize("hasRole('LAB_TECHNICIAN')")
    public ResponseEntity<LabTestResponse> accept(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(labTestService.acceptTest(id)));
    }

    @PatchMapping("/{id}/collect-sample")
    @PreAuthorize("hasRole('LAB_TECHNICIAN')")
    public ResponseEntity<LabTestResponse> collectSample(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(labTestService.collectSample(id)));
    }

    @PatchMapping("/{id}/process")
    @PreAuthorize("hasRole('LAB_TECHNICIAN')")
    public ResponseEntity<LabTestResponse> process(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(labTestService.startProcessing(id)));
    }

    @PatchMapping("/{id}/complete")
    @PreAuthorize("hasRole('LAB_TECHNICIAN')")
    public ResponseEntity<LabTestResponse> complete(@PathVariable Long id,
                                                      @RequestParam String resultSummary,
                                                      @RequestParam(required = false) String reportFileUrl) {
        return ResponseEntity.ok(mapToResponse(labTestService.completeTest(id, resultSummary, reportFileUrl)));
    }

    private Doctor getDoctorFromAuth(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return doctorService.getDoctorByUserId(user.getId());
    }

    private LabTestResponse mapToResponse(LabTestRequest t) {
        return new LabTestResponse(t.getId(),
                t.getConsultation() != null ? t.getConsultation().getId() : null,
                t.getDoctor().getId(), t.getDoctor().getUser().getName(),
                t.getPatient() != null ? t.getPatient().getId() : null,
                t.getPatient() != null ? t.getPatient().getUser().getName() : null,
                t.getLaboratory().getId(), t.getLaboratory().getName(),
                t.getTestName(), t.getInstructions(), t.getStatus(),
                t.getResultSummary(), t.getReportFileUrl(), t.getCompletedAt(), t.getCreatedAt());
    }
}
