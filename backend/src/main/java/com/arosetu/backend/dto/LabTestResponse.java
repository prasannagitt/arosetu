package com.arosetu.backend.dto;

import com.arosetu.backend.enums.LabTestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LabTestResponse {
    private Long id;
    private Long consultationId;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private Long laboratoryId;
    private String laboratoryName;
    private String testName;
    private String instructions;
    private LabTestStatus status;
    private String resultSummary;
    private String reportFileUrl;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
}
