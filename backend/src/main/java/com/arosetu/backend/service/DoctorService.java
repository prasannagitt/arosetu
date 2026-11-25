package com.arosetu.backend.service;

import com.arosetu.backend.dto.DoctorResponse;
import com.arosetu.backend.dto.DoctorSearchResponse;
import com.arosetu.backend.entity.Doctor;
import com.arosetu.backend.entity.DoctorHospital;
import com.arosetu.backend.enums.ApprovalStatus;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.DoctorHospitalRepository;
import com.arosetu.backend.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorHospitalRepository doctorHospitalRepository;

    public DoctorService(DoctorRepository doctorRepository, DoctorHospitalRepository doctorHospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorHospitalRepository = doctorHospitalRepository;
    }

    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor getDoctorEntityById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    public Doctor getDoctorByUserId(Long userId) {
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor profile not found for user id: " + userId));
    }

    public Optional<Doctor> getDoctorByLicenseNumber(String licenseNumber) {
        return doctorRepository.findByLicenseNumber(licenseNumber);
    }

    @Transactional
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        existingDoctor.setQualification(updatedDoctor.getQualification());
        existingDoctor.setExperienceYears(updatedDoctor.getExperienceYears());
        existingDoctor.setLicenseNumber(updatedDoctor.getLicenseNumber());
        existingDoctor.setConsultationFee(updatedDoctor.getConsultationFee());
        existingDoctor.setAbout(updatedDoctor.getAbout());
        return doctorRepository.save(existingDoctor);
    }

    public List<Doctor> getApprovedDoctors() {
        return doctorRepository.findAll().stream()
                .filter(d -> d.getApprovalStatus() == ApprovalStatus.APPROVED)
                .collect(Collectors.toList());
    }

    public List<DoctorSearchResponse> searchDoctors(String specialization, String city) {
        List<Doctor> doctors = doctorRepository.findAll().stream()
                .filter(d -> d.getApprovalStatus() == ApprovalStatus.APPROVED)
                .filter(d -> specialization == null || specialization.isEmpty() ||
                        (d.getSpecialization() != null && d.getSpecialization().toLowerCase().contains(specialization.toLowerCase())))
                .collect(Collectors.toList());

        return doctors.stream()
                .map(doctor -> {
                    List<DoctorHospital> associations = doctorHospitalRepository.findByDoctorId(doctor.getId());
                    List<String> hospitalNames = associations.stream()
                            .map(dh -> dh.getHospital().getName())
                            .collect(Collectors.toList());

                    if (city != null && !city.isEmpty()) {
                        boolean hasHospitalInCity = associations.stream()
                                .anyMatch(dh -> dh.getHospital().getCity() != null &&
                                        dh.getHospital().getCity().toLowerCase().contains(city.toLowerCase()));
                        if (!hasHospitalInCity) return null;
                    }

                    return new DoctorSearchResponse(
                            doctor.getId(),
                            doctor.getUser().getName(),
                            doctor.getSpecialization(),
                            doctor.getQualification(),
                            doctor.getExperienceYears(),
                            doctor.getConsultationFee(),
                            doctor.getAbout(),
                            hospitalNames
                    );
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    public Doctor approveDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctor.setApprovalStatus(ApprovalStatus.APPROVED);
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor rejectDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctor.setApprovalStatus(ApprovalStatus.REJECTED);
        return doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }

    public DoctorResponse mapToResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getUser().getName(),
                doctor.getUser().getEmail(),
                doctor.getUser().getPhone(),
                doctor.getSpecialization(),
                doctor.getQualification(),
                doctor.getExperienceYears(),
                doctor.getLicenseNumber(),
                doctor.getConsultationFee(),
                doctor.getAbout(),
                doctor.getApprovalStatus()
        );
    }
}
