package com.arosetu.backend.controller;

import com.arosetu.backend.entity.User;
import com.arosetu.backend.enums.ApprovalStatus;
import com.arosetu.backend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final HospitalService hospitalService;
    private final LaboratoryService laboratoryService;
    private final PharmacyService pharmacyService;

    public AdminController(UserService userService, DoctorService doctorService,
                           PatientService patientService, HospitalService hospitalService,
                           LaboratoryService laboratoryService, PharmacyService pharmacyService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.hospitalService = hospitalService;
        this.laboratoryService = laboratoryService;
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/users/{id}/toggle")
    public ResponseEntity<User> toggleUser(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(!user.isActive());
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @GetMapping("/doctors/pending")
    public ResponseEntity<?> getPendingDoctors() {
        var doctors = doctorService.getAllDoctors().stream()
                .filter(d -> d.getApprovalStatus() == ApprovalStatus.PENDING)
                .toList();
        return ResponseEntity.ok(doctors);
    }

    @PatchMapping("/doctors/{id}/approve")
    public ResponseEntity<?> approveDoctor(@PathVariable Long id) {
        var doctor = doctorService.getDoctorById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setApprovalStatus(ApprovalStatus.APPROVED);
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
    }

    @PatchMapping("/doctors/{id}/reject")
    public ResponseEntity<?> rejectDoctor(@PathVariable Long id) {
        var doctor = doctorService.getDoctorById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setApprovalStatus(ApprovalStatus.REJECTED);
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", (long) userService.getAllUsers().size());
        stats.put("totalDoctors", (long) doctorService.getAllDoctors().size());
        stats.put("totalPatients", (long) patientService.getAllPatients().size());
        stats.put("totalHospitals", (long) hospitalService.getAllHospitals().size());
        stats.put("totalLaboratories", (long) laboratoryService.getAllLaboratories().size());
        stats.put("totalPharmacies", (long) pharmacyService.getAllPharmacies().size());
        return ResponseEntity.ok(stats);
    }
}

