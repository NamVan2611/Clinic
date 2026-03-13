package com.clinic.dto;

import com.clinic.entity.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {

    private Long id;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Appointment time is required")
    private LocalDateTime appointmentTime;

    private String room;

    @NotNull(message = "Status is required")
    @Builder.Default
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
}
