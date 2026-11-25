package com.arosetu.backend.controller;

import com.arosetu.backend.dto.HospitalRequest;
import com.arosetu.backend.dto.HospitalResponse;
import com.arosetu.backend.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/public")
    public ResponseEntity<List<HospitalResponse>> getActiveHospitals() {
        return ResponseEntity.ok(hospitalService.getActiveHospitals());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HospitalResponse>> getAllHospitals() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HospitalResponse> createHospital(@Valid @RequestBody HospitalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.createHospital(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HospitalResponse> updateHospital(@PathVariable Long id, @Valid @RequestBody HospitalRequest request) {
        return ResponseEntity.ok(hospitalService.updateHospital(id, request));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> toggleActive(@PathVariable Long id) {
        hospitalService.toggleHospitalActive(id);
        return ResponseEntity.ok().build();
    }
}
