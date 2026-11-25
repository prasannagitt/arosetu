package com.arosetu.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class AvailabilityResponse {
    private Long id;
    private Long doctorId;
    private String doctorName;
    private Long hospitalId;
    private String hospitalName;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer slotDurationMinutes;
}
