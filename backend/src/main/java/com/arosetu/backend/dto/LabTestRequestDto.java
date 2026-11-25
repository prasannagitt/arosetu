package com.arosetu.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabTestRequestDto {

    private Long consultationId;

    @NotNull(message = "Laboratory ID is required")
    private Long laboratoryId;

    @NotBlank(message = "Test name is required")
    private String testName;

    private String instructions;
}
