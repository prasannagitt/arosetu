package com.arosetu.backend.dto;

import com.arosetu.backend.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DoctorResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String specialization;
    private String qualification;
    private Integer experienceYears;
    private String licenseNumber;
    private BigDecimal consultationFee;
    private String about;
    private ApprovalStatus approvalStatus;
}
