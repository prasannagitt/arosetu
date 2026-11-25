package com.arosetu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class DoctorSearchResponse {
    private Long id;
    private String name;
    private String specialization;
    private String qualification;
    private Integer experienceYears;
    private BigDecimal consultationFee;
    private String about;
    private List<String> hospitals;
}
