package com.arosetu.backend.controller;

import com.arosetu.backend.dto.DepartmentRequest;
import com.arosetu.backend.dto.DepartmentResponse;
import com.arosetu.backend.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<DepartmentResponse>> getByHospital(@PathVariable Long hospitalId) {
        return ResponseEntity.ok(departmentService.getDepartmentsByHospital(hospitalId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponse> update(@PathVariable Long id,
                                                      @RequestBody DepartmentRequest request) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, request));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> toggle(@PathVariable Long id) {
        departmentService.toggleDepartmentActive(id);
        return ResponseEntity.ok().build();
    }
}
