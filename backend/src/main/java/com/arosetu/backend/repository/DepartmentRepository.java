package com.arosetu.backend.repository;

import com.arosetu.backend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByHospitalId(Long hospitalId);
    List<Department> findByHospitalIdAndActiveTrue(Long hospitalId);
}

