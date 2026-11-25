package com.arosetu.backend.dto;

import com.arosetu.backend.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class AppointmentResponse {
    private Long id;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private Long hospitalId;
    private String hospitalName;
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private AppointmentStatus status;
    private String reason;
    private LocalDateTime createdAt;
}
