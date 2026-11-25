package com.arosetu.backend.repository;

import com.arosetu.backend.entity.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
    List<Laboratory> findByActiveTrue();
}

