package com.arosetu.backend.controller;

import com.arosetu.backend.entity.Pharmacy;
import com.arosetu.backend.service.PharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAll() {
        return ResponseEntity.ok(pharmacyService.getAllPharmacies());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Pharmacy>> getActive() {
        return ResponseEntity.ok(pharmacyService.getActivePharmacies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pharmacyService.getPharmacyById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Pharmacy> create(@RequestBody Pharmacy pharmacy) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pharmacyService.createPharmacy(pharmacy));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Pharmacy> update(@PathVariable Long id, @RequestBody Pharmacy pharmacy) {
        return ResponseEntity.ok(pharmacyService.updatePharmacy(id, pharmacy));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Pharmacy> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(pharmacyService.togglePharmacyActive(id));
    }
}

