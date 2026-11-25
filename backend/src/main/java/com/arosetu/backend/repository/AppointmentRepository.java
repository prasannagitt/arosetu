package com.arosetu.backend.repository;

import com.arosetu.backend.entity.Appointment;
import com.arosetu.backend.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
    List<Appointment> findByDoctorIdAndAppointmentDateAndStatus(Long doctorId, LocalDate date, AppointmentStatus status);
    Optional<Appointment> findByDoctorIdAndAppointmentDateAndStartTimeAndStatusNot(Long doctorId, LocalDate date, LocalTime startTime, AppointmentStatus status);
}

