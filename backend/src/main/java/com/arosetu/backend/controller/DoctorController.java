package com.arosetu.backend.controller;

import com.arosetu.backend.dto.DoctorProfileRequest;
import com.arosetu.backend.dto.DoctorResponse;
import com.arosetu.backend.dto.DoctorSearchResponse;
import com.arosetu.backend.entity.Doctor;
import com.arosetu.backend.entity.User;
import com.arosetu.backend.service.DoctorService;
import com.arosetu.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final UserService userService;

    public DoctorController(DoctorService doctorService, UserService userService) {
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllApprovedDoctors() {
        List<DoctorResponse> doctors = doctorService.getApprovedDoctors().stream()
                .map(doctorService::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorEntityById(id);
        return ResponseEntity.ok(doctorService.mapToResponse(doctor));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorSearchResponse>> searchDoctors(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String city) {
        return ResponseEntity.ok(doctorService.searchDoctors(specialization, city));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<DoctorResponse> getMyProfile(Authentication auth) {
        User user = userService.getUserEntityByEmail(auth.getName());
        Doctor doctor = doctorService.getDoctorByUserId(user.getId());
        return ResponseEntity.ok(doctorService.mapToResponse(doctor));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<DoctorResponse> updateMyProfile(Authentication auth, @RequestBody DoctorProfileRequest request) {
        User user = userService.getUserEntityByEmail(auth.getName());
        Doctor doctor = doctorService.getDoctorByUserId(user.getId());

        Doctor updateData = new Doctor();
        updateData.setSpecialization(request.getSpecialization());
        updateData.setQualification(request.getQualification());
        updateData.setExperienceYears(request.getExperienceYears());
        updateData.setLicenseNumber(request.getLicenseNumber());
        updateData.setConsultationFee(request.getConsultationFee());
        updateData.setAbout(request.getAbout());

        Doctor updated = doctorService.updateDoctor(doctor.getId(), updateData);
        return ResponseEntity.ok(doctorService.mapToResponse(updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
