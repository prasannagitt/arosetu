package com.arosetu.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionItemRequest {

    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;
}
