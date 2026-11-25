package com.arosetu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ConsultationResponse {
    private Long id;
    private Long appointmentId;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String symptoms;
    private String observations;
    private String diagnosis;
    private String clinicalNotes;
    private String treatmentPlan;
    private LocalDateTime consultationDate;
}
