package com.arosetu.backend.service;

import com.arosetu.backend.dto.HospitalRequest;
import com.arosetu.backend.dto.HospitalResponse;
import com.arosetu.backend.entity.Hospital;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public HospitalResponse createHospital(HospitalRequest request) {
        Hospital hospital = new Hospital();
        hospital.setName(request.getName());
        hospital.setType(request.getType());
        hospital.setAddress(request.getAddress());
        hospital.setCity(request.getCity());
        hospital.setState(request.getState());
        hospital.setPinCode(request.getPinCode());
        hospital.setContactNumber(request.getContactNumber());
        hospital.setEmail(request.getEmail());
        hospital.setActive(true);
        Hospital saved = hospitalRepository.save(hospital);
        return mapToResponse(saved);
    }

    public List<HospitalResponse> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<HospitalResponse> getActiveHospitals() {
        return hospitalRepository.findByActiveTrue().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public HospitalResponse getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + id));
        return mapToResponse(hospital);
    }

    public Hospital getHospitalEntityById(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + id));
    }

    @Transactional
    public HospitalResponse updateHospital(Long id, HospitalRequest request) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + id));
        hospital.setName(request.getName());
        hospital.setType(request.getType());
        hospital.setAddress(request.getAddress());
        hospital.setCity(request.getCity());
        hospital.setState(request.getState());
        hospital.setPinCode(request.getPinCode());
        hospital.setContactNumber(request.getContactNumber());
        hospital.setEmail(request.getEmail());
        Hospital saved = hospitalRepository.save(hospital);
        return mapToResponse(saved);
    }

    @Transactional
    public void toggleHospitalActive(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + id));
        hospital.setActive(!hospital.isActive());
        hospitalRepository.save(hospital);
    }

    public List<HospitalResponse> getHospitalsByCity(String city) {
        return hospitalRepository.findByCity(city).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HospitalResponse mapToResponse(Hospital hospital) {
        return new HospitalResponse(
                hospital.getId(),
                hospital.getName(),
                hospital.getType(),
                hospital.getAddress(),
                hospital.getCity(),
                hospital.getState(),
                hospital.getPinCode(),
                hospital.getContactNumber(),
                hospital.getEmail(),
                hospital.isActive()
        );
    }
}
