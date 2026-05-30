package com.clinic.dto;

import com.clinic.entity.AppointmentStatus;
import com.clinic.validation.FutureDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @FutureDateTime
    private LocalDateTime appointmentTime;

    @NotBlank(message = "Room is required")
    @Size(max = 50, message = "Room name cannot exceed 50 characters")
    private String room;

    @NotNull(message = "Status is required")
    @Builder.Default
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
}
