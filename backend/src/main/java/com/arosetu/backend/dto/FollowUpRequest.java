package com.arosetu.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class FollowUpRequest {

    @NotNull(message = "Consultation ID is required")
    private Long consultationId;

    @NotNull(message = "Follow-up date is required")
    private LocalDate followUpDate;

    private String reason;
    private String instructions;
}
