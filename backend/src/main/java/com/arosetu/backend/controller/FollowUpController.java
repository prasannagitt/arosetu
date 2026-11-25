package com.arosetu.backend.controller;

import com.arosetu.backend.dto.FollowUpRequest;
import com.arosetu.backend.dto.FollowUpResponse;
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
@RequestMapping("/api/follow-ups")
public class FollowUpController {

    private final FollowUpService followUpService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public FollowUpController(FollowUpService followUpService, UserService userService,
                               DoctorService doctorService, PatientService patientService) {
        this.followUpService = followUpService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<FollowUpResponse> create(@Valid @RequestBody FollowUpRequest request,
                                                     Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        Doctor doctor = doctorService.getDoctorByUserId(user.getId());
        FollowUp f = followUpService.createFollowUp(doctor.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(f));
    }

    @GetMapping("/me")
    public ResponseEntity<List<FollowUpResponse>> getMy(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        List<FollowUp> list;
        if (user.getRole() == Role.PATIENT) {
            Patient patient = patientService.getPatientByUserId(user.getId());
            list = followUpService.getFollowUpsByPatient(patient.getId());
        } else {
            Doctor doctor = doctorService.getDoctorByUserId(user.getId());
            list = followUpService.getFollowUpsByDoctor(doctor.getId());
        }
        return ResponseEntity.ok(list.stream().map(this::mapToResponse).toList());
    }

    @GetMapping("/upcoming")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<FollowUpResponse>> getUpcoming(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        Patient patient = patientService.getPatientByUserId(user.getId());
        return ResponseEntity.ok(followUpService.getUpcomingFollowUps(patient.getId())
                .stream().map(this::mapToResponse).toList());
    }

    private FollowUpResponse mapToResponse(FollowUp f) {
        return new FollowUpResponse(f.getId(), f.getConsultation().getId(),
                f.getDoctor().getId(), f.getDoctor().getUser().getName(),
                f.getPatient().getId(), f.getPatient().getUser().getName(),
                f.getFollowUpDate(), f.getReason(), f.getInstructions());
    }
}
