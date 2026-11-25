package com.arosetu.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest {

    @NotBlank(message = "Department name is required")
    private String name;

    @NotNull(message = "Hospital ID is required")
    private Long hospitalId;
}
