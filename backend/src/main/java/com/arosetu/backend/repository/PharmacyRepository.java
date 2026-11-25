package com.arosetu.backend.repository;

import com.arosetu.backend.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findByActiveTrue();
}

