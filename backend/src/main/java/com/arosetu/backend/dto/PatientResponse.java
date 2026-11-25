package com.arosetu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PatientResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String address;
    private String emergencyContact;
}
