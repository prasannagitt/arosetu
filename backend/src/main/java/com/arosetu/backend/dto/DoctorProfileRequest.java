package com.arosetu.backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class DoctorProfileRequest {
    private String specialization;
    private String qualification;
    private Integer experienceYears;
    private String licenseNumber;
    private BigDecimal consultationFee;
    private String about;
}
