package com.arosetu.backend.service;

import com.arosetu.backend.entity.DoctorHospital;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.DoctorHospitalRepository;
import com.arosetu.backend.repository.DoctorRepository;
import com.arosetu.backend.repository.HospitalRepository;
import com.arosetu.backend.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorHospitalService {

    private final DoctorHospitalRepository doctorHospitalRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;

    public DoctorHospitalService(DoctorHospitalRepository doctorHospitalRepository,
                                  DoctorRepository doctorRepository,
                                  HospitalRepository hospitalRepository,
                                  DepartmentRepository departmentRepository) {
        this.doctorHospitalRepository = doctorHospitalRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public DoctorHospital associateDoctor(Long doctorId, Long hospitalId, Long departmentId) {
        var doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        var hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        DoctorHospital dh = new DoctorHospital();
        dh.setDoctor(doctor);
        dh.setHospital(hospital);
        if (departmentId != null) {
            var dept = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            dh.setDepartment(dept);
        }
        return doctorHospitalRepository.save(dh);
    }

    public List<DoctorHospital> getHospitalsByDoctor(Long doctorId) {
        return doctorHospitalRepository.findByDoctorId(doctorId);
    }

    public List<DoctorHospital> getDoctorsByHospital(Long hospitalId) {
        return doctorHospitalRepository.findByHospitalId(hospitalId);
    }

    @Transactional
    public void removeAssociation(Long id) {
        if (!doctorHospitalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Association not found");
        }
        doctorHospitalRepository.deleteById(id);
    }
}

