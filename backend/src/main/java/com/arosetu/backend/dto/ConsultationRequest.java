package com.arosetu.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultationRequest {

    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    private String symptoms;
    private String observations;
    private String diagnosis;
    private String clinicalNotes;
    private String treatmentPlan;
}
