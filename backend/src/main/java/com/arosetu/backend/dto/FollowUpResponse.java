package com.arosetu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FollowUpResponse {
    private Long id;
    private Long consultationId;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private LocalDate followUpDate;
    private String reason;
    private String instructions;
}
