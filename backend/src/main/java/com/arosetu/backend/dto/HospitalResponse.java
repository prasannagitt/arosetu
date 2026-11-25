package com.arosetu.backend.dto;

import com.arosetu.backend.enums.HospitalType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalResponse {
    private Long id;
    private String name;
    private HospitalType type;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String contactNumber;
    private String email;
    private boolean active;
}
