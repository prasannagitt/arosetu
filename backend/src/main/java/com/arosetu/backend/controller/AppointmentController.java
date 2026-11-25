package com.arosetu.backend.controller;

import com.arosetu.backend.dto.AppointmentRequest;
import com.arosetu.backend.dto.AppointmentResponse;
import com.arosetu.backend.entity.Appointment;
import com.arosetu.backend.entity.Doctor;
import com.arosetu.backend.entity.Patient;
import com.arosetu.backend.entity.User;
import com.arosetu.backend.enums.Role;
import com.arosetu.backend.service.AppointmentService;
import com.arosetu.backend.service.DoctorService;
import com.arosetu.backend.service.PatientService;
import com.arosetu.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, UserService userService,
                                  PatientService patientService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody AppointmentRequest request,
                                                                   Authentication auth) {
        Patient patient = getPatientFromAuth(auth);
        Appointment a = appointmentService.createAppointment(patient.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(a));
    }

    @GetMapping("/me")
    public ResponseEntity<List<AppointmentResponse>> getMyAppointments(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        List<Appointment> appointments;
        if (user.getRole() == Role.PATIENT) {
            Patient patient = patientService.getPatientByUserId(user.getId());
            appointments = appointmentService.getAppointmentsByPatient(patient.getId());
        } else {
            Doctor doctor = doctorService.getDoctorByUserId(user.getId());
            appointments = appointmentService.getAppointmentsByDoctor(doctor.getId());
        }
        return ResponseEntity.ok(appointments.stream().map(this::mapToResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(appointmentService.getAppointmentById(id)));
    }

    @PatchMapping("/{id}/confirm")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AppointmentResponse> confirm(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(appointmentService.confirmAppointment(id)));
    }

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AppointmentResponse> reject(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(appointmentService.rejectAppointment(id)));
    }

    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasAnyRole('PATIENT','DOCTOR')")
    public ResponseEntity<AppointmentResponse> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(appointmentService.cancelAppointment(id)));
    }

    @PatchMapping("/{id}/complete")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AppointmentResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(appointmentService.completeAppointment(id)));
    }

    private Patient getPatientFromAuth(Authentication auth) {
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return patientService.getPatientByUserId(user.getId());
    }

    private AppointmentResponse mapToResponse(Appointment a) {
        return new AppointmentResponse(a.getId(), a.getPatient().getId(),
                a.getPatient().getUser().getName(), a.getDoctor().getId(),
                a.getDoctor().getUser().getName(), a.getHospital().getId(),
                a.getHospital().getName(), a.getAppointmentDate(), a.getStartTime(),
                a.getEndTime(), a.getStatus(), a.getReason(), a.getCreatedAt());
    }
}
