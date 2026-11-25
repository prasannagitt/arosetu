package com.arosetu.backend.service;

import com.arosetu.backend.entity.Laboratory;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.LaboratoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaboratoryService {

    private final LaboratoryRepository laboratoryRepository;

    public LaboratoryService(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    @Transactional
    public Laboratory createLaboratory(Laboratory laboratory) {
        return laboratoryRepository.save(laboratory);
    }

    public List<Laboratory> getAllLaboratories() {
        return laboratoryRepository.findAll();
    }

    public List<Laboratory> getActiveLaboratories() {
        return laboratoryRepository.findByActiveTrue();
    }

    public Laboratory getLaboratoryById(Long id) {
        return laboratoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratory not found"));
    }

    @Transactional
    public Laboratory updateLaboratory(Long id, Laboratory updated) {
        Laboratory existing = getLaboratoryById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setContactNumber(updated.getContactNumber());
        return laboratoryRepository.save(existing);
    }

    @Transactional
    public Laboratory toggleLaboratoryActive(Long id) {
        Laboratory lab = getLaboratoryById(id);
        lab.setActive(!lab.isActive());
        return laboratoryRepository.save(lab);
    }
}

