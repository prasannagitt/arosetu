package com.arosetu.backend.service;

import com.arosetu.backend.dto.AvailabilityRequest;
import com.arosetu.backend.entity.Appointment;
import com.arosetu.backend.entity.Availability;
import com.arosetu.backend.enums.AppointmentStatus;
import com.arosetu.backend.exception.ResourceNotFoundException;
import com.arosetu.backend.repository.AppointmentRepository;
import com.arosetu.backend.repository.AvailabilityRepository;
import com.arosetu.backend.repository.DoctorRepository;
import com.arosetu.backend.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository,
                               DoctorRepository doctorRepository,
                               HospitalRepository hospitalRepository,
                               AppointmentRepository appointmentRepository) {
        this.availabilityRepository = availabilityRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public Availability createAvailability(Long doctorId, AvailabilityRequest request) {
        var doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        var hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        Availability availability = new Availability();
        availability.setDoctor(doctor);
        availability.setHospital(hospital);
        availability.setDayOfWeek(request.getDayOfWeek());
        availability.setStartTime(request.getStartTime());
        availability.setEndTime(request.getEndTime());
        availability.setSlotDurationMinutes(request.getSlotDurationMinutes() != null ? request.getSlotDurationMinutes() : 30);

        return availabilityRepository.save(availability);
    }

    public List<Availability> getAvailabilityByDoctor(Long doctorId) {
        return availabilityRepository.findByDoctorId(doctorId);
    }

    public List<Availability> getAvailabilityByDoctorAndHospital(Long doctorId, Long hospitalId) {
        return availabilityRepository.findByDoctorIdAndHospitalId(doctorId, hospitalId);
    }

    @Transactional
    public Availability updateAvailability(Long id, AvailabilityRequest request) {
        Availability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found"));
        existing.setDayOfWeek(request.getDayOfWeek());
        existing.setStartTime(request.getStartTime());
        existing.setEndTime(request.getEndTime());
        if (request.getSlotDurationMinutes() != null) {
            existing.setSlotDurationMinutes(request.getSlotDurationMinutes());
        }
        return availabilityRepository.save(existing);
    }

    @Transactional
    public void deleteAvailability(Long id) {
        if (!availabilityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Availability not found");
        }
        availabilityRepository.deleteById(id);
    }

    public List<LocalTime> getAvailableSlots(Long doctorId, Long hospitalId, LocalDate date) {
        var dayOfWeek = date.getDayOfWeek();
        var availability = availabilityRepository
                .findByDoctorIdAndHospitalIdAndDayOfWeek(doctorId, hospitalId, dayOfWeek)
                .orElse(null);

        if (availability == null) {
            return new ArrayList<>();
        }

        // Generate all possible slots
        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime current = availability.getStartTime();
        while (current.plusMinutes(availability.getSlotDurationMinutes()).isBefore(availability.getEndTime())
                || current.plusMinutes(availability.getSlotDurationMinutes()).equals(availability.getEndTime())) {
            allSlots.add(current);
            current = current.plusMinutes(availability.getSlotDurationMinutes());
        }

        // Get booked slots for this date (exclude cancelled)
        List<Appointment> bookedAppointments = appointmentRepository
                .findByDoctorIdAndAppointmentDate(doctorId, date);
        Set<LocalTime> bookedTimes = bookedAppointments.stream()
                .filter(a -> a.getStatus() != AppointmentStatus.CANCELLED && a.getStatus() != AppointmentStatus.REJECTED)
                .map(Appointment::getStartTime)
                .collect(Collectors.toSet());

        // Remove booked slots
        allSlots.removeIf(bookedTimes::contains);
        return allSlots;
    }
}

