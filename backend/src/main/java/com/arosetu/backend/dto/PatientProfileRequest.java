package com.arosetu.backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PatientProfileRequest {
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String address;
    private String emergencyContact;
}
