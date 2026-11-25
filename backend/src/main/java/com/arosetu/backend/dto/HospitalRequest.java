package com.arosetu.backend.dto;

import com.arosetu.backend.enums.HospitalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Hospital type is required")
    private HospitalType type;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    private String pinCode;
    private String contactNumber;
    private String email;
}
