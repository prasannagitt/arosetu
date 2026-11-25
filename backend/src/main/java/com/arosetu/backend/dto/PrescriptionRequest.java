package com.arosetu.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PrescriptionRequest {

    @NotNull(message = "Consultation ID is required")
    private Long consultationId;

    private String notes;

    @NotNull(message = "Prescription items are required")
    private List<PrescriptionItemRequest> items;
}
