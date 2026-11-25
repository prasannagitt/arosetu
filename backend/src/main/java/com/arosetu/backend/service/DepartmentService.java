package com.arosetu.backend.service;

import com.arosetu.backend.dto.DepartmentRequest;
import com.arosetu.backend.dto.DepartmentResponse;
import com.arosetu.backend.entity.Department;
import com.arosetu.backend.entity.Hospital;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.DepartmentRepository;
import com.arosetu.backend.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public DepartmentService(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository) {
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + request.getHospitalId()));
        Department department = new Department();
        department.setName(request.getName());
        department.setHospital(hospital);
        department.setActive(true);
        Department saved = departmentRepository.save(department);
        return mapToResponse(saved);
    }

    public List<DepartmentResponse> getDepartmentsByHospital(Long hospitalId) {
        return departmentRepository.findByHospitalIdAndActiveTrue(hospitalId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return mapToResponse(department);
    }

    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        department.setName(request.getName());
        if (request.getHospitalId() != null) {
            Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + request.getHospitalId()));
            department.setHospital(hospital);
        }
        Department saved = departmentRepository.save(department);
        return mapToResponse(saved);
    }

    @Transactional
    public void toggleDepartmentActive(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        department.setActive(!department.isActive());
        departmentRepository.save(department);
    }

    private DepartmentResponse mapToResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getHospital().getId(),
                department.getHospital().getName(),
                department.isActive()
        );
    }
}
