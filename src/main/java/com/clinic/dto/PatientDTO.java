package com.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;

    private LocalDate dob;

    @Size(max = 20)
    private String phone;

    @Size(max = 500)
    private String address;

    private String medicalHistory;

    private LocalDateTime createdAt;
}
