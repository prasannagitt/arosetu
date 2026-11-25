package com.arosetu.backend.controller;

import com.arosetu.backend.dto.AvailabilityRequest;
import com.arosetu.backend.dto.AvailabilityResponse;
import com.arosetu.backend.entity.Availability;
import com.arosetu.backend.entity.Doctor;
import com.arosetu.backend.service.AvailabilityService;
import com.arosetu.backend.service.DoctorService;
import com.arosetu.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final UserService userService;
    private final DoctorService doctorService;

    public AvailabilityController(AvailabilityService availabilityService,
                                   UserService userService, DoctorService doctorService) {
        this.availabilityService = availabilityService;
        this.userService = userService;
        this.doctorService = doctorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AvailabilityResponse> createAvailability(@Valid @RequestBody AvailabilityRequest request,
                                                                     Authentication auth) {
        Doctor doctor = getDoctorFromAuth(auth);
        Availability a = availabilityService.createAvailability(doctor.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(a));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<AvailabilityResponse>> getMyAvailability(Authentication auth) {
        Doctor doctor = getDoctorFromAuth(auth);
        return ResponseEntity.ok(availabilityService.getAvailabilityByDoctor(doctor.getId())
                .stream().map(this::mapToResponse).toList());
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AvailabilityResponse>> getByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(availabilityService.getAvailabilityByDoctor(doctorId)
                .stream().map(this::mapToResponse).toList());
    }

    @GetMapping("/slots")
    public ResponseEntity<List<LocalTime>> getAvailableSlots(@RequestParam Long doctorId,
                                                              @RequestParam Long hospitalId,
                                                              @RequestParam LocalDate date) {
        return ResponseEntity.ok(availabilityService.getAvailableSlots(doctorId, hospitalId, date));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AvailabilityResponse> updateAvailability(@PathVariable Long id,
                                                                     @Valid @RequestBody AvailabilityRequest request) {
        return ResponseEntity.ok(mapToResponse(availabilityService.updateAvailability(id, request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }

    private Doctor getDoctorFromAuth(Authentication auth) {
        var user = userService.getUserEntityByEmail(auth.getName());
        return doctorService.getDoctorByUserId(user.getId());
    }

    private AvailabilityResponse mapToResponse(Availability a) {
        return new AvailabilityResponse(a.getId(), a.getDoctor().getId(),
                a.getDoctor().getUser().getName(), a.getHospital().getId(),
                a.getHospital().getName(), a.getDayOfWeek(), a.getStartTime(),
                a.getEndTime(), a.getSlotDurationMinutes());
    }
}
