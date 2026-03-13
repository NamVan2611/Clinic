package com.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String specialization;

    private String phone;

    private String email;
}
