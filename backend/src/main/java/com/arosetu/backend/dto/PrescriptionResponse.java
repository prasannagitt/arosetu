package com.arosetu.backend.dto;

import com.arosetu.backend.enums.PrescriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PrescriptionResponse {
    private Long id;
    private Long consultationId;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private Long pharmacyId;
    private String pharmacyName;
    private PrescriptionStatus status;
    private String notes;
    private List<PrescriptionItemResponse> items;
    private LocalDateTime createdAt;
}
