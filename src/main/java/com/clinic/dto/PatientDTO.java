package com.clinic.dto;

import com.clinic.validation.PastOrToday;
import com.clinic.validation.ValidPhone;
import jakarta.validation.constraints.Email;
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
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    private String name;

    @PastOrToday(message = "Date of birth must be in the past")
    private LocalDate dob;

    @ValidPhone
    private String phone;

    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

    @Email(message = "Email must be valid")
    private String email;

    private String medicalHistory;

    private LocalDateTime createdAt;
}
