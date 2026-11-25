package com.arosetu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentResponse {
    private Long id;
    private String name;
    private Long hospitalId;
    private String hospitalName;
    private boolean active;
}
