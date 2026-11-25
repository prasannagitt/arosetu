package com.arosetu.backend.service;

import com.arosetu.backend.entity.Pharmacy;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.PharmacyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    @Transactional
    public Pharmacy createPharmacy(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    public List<Pharmacy> getActivePharmacies() {
        return pharmacyRepository.findByActiveTrue();
    }

    public Pharmacy getPharmacyById(Long id) {
        return pharmacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found"));
    }

    @Transactional
    public Pharmacy updatePharmacy(Long id, Pharmacy updated) {
        Pharmacy existing = getPharmacyById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setContactNumber(updated.getContactNumber());
        return pharmacyRepository.save(existing);
    }

    @Transactional
    public Pharmacy togglePharmacyActive(Long id) {
        Pharmacy pharmacy = getPharmacyById(id);
        pharmacy.setActive(!pharmacy.isActive());
        return pharmacyRepository.save(pharmacy);
    }
}

