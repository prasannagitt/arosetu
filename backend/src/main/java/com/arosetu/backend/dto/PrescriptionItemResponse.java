package com.arosetu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PrescriptionItemResponse {
    private Long id;
    private String medicineName;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;
}
