package com.arosetu.backend.entity;

import com.arosetu.backend.enums.LabTestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lab_test_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabTestRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "laboratory_id", nullable = false)
    private Laboratory laboratory;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private LabTestStatus status = LabTestStatus.REQUESTED;

    @Column(name = "result_summary", columnDefinition = "TEXT")
    private String resultSummary;

    @Column(name = "report_file_url")
    private String reportFileUrl;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
