package com.arosetu.backend.controller;

import com.arosetu.backend.entity.Laboratory;
import com.arosetu.backend.service.LaboratoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratories")
public class LaboratoryController {

    private final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @GetMapping
    public ResponseEntity<List<Laboratory>> getAll() {
        return ResponseEntity.ok(laboratoryService.getAllLaboratories());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Laboratory>> getActive() {
        return ResponseEntity.ok(laboratoryService.getActiveLaboratories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(laboratoryService.getLaboratoryById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Laboratory> create(@RequestBody Laboratory laboratory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratoryService.createLaboratory(laboratory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Laboratory> update(@PathVariable Long id, @RequestBody Laboratory laboratory) {
        return ResponseEntity.ok(laboratoryService.updateLaboratory(id, laboratory));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Laboratory> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(laboratoryService.toggleLaboratoryActive(id));
    }
}

