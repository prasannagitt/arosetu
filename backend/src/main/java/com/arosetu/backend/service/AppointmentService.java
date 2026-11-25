package com.arosetu.backend.service;

import com.arosetu.backend.dto.AppointmentRequest;
import com.arosetu.backend.entity.Appointment;
import com.arosetu.backend.entity.Availability;
import com.arosetu.backend.enums.AppointmentStatus;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final AvailabilityRepository availabilityRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository,
                              HospitalRepository hospitalRepository,
                              AvailabilityRepository availabilityRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.availabilityRepository = availabilityRepository;
    }

    @Transactional
    public Appointment createAppointment(Long patientId, AppointmentRequest request) {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        var doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        var hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        // Check for double booking
        var existing = appointmentRepository.findByDoctorIdAndAppointmentDateAndStartTimeAndStatusNot(
                request.getDoctorId(), request.getAppointmentDate(), request.getStartTime(), AppointmentStatus.CANCELLED);
        if (existing.isPresent()) {
            throw new IllegalStateException("This time slot is already booked");
        }

        // Calculate end time from availability
        var dayOfWeek = request.getAppointmentDate().getDayOfWeek();
        Availability availability = availabilityRepository
                .findByDoctorIdAndHospitalIdAndDayOfWeek(request.getDoctorId(), request.getHospitalId(), dayOfWeek)
                .orElse(null);
        int slotMinutes = availability != null ? availability.getSlotDurationMinutes() : 30;

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setHospital(hospital);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getStartTime().plusMinutes(slotMinutes));
        appointment.setStatus(AppointmentStatus.REQUESTED);
        appointment.setReason(request.getReason());

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    @Transactional
    public Appointment confirmAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
            throw new IllegalStateException("Can only confirm REQUESTED appointments");
        }
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment rejectAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
            throw new IllegalStateException("Can only reject REQUESTED appointments");
        }
        appointment.setStatus(AppointmentStatus.REJECTED);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment cancelAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment.getStatus() != AppointmentStatus.REQUESTED && appointment.getStatus() != AppointmentStatus.CONFIRMED) {
            throw new IllegalStateException("Can only cancel REQUESTED or CONFIRMED appointments");
        }
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment completeAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment.getStatus() != AppointmentStatus.CONFIRMED) {
            throw new IllegalStateException("Can only complete CONFIRMED appointments");
        }
        appointment.setStatus(AppointmentStatus.COMPLETED);
        return appointmentRepository.save(appointment);
    }
}

