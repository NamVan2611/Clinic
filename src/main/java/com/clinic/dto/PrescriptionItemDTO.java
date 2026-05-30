package com.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemDTO {

    private Long id;

    private Long prescriptionId;

    @NotBlank(message = "Medicine name is required")
    @Size(min = 2, max = 255, message = "Medicine name must be between 2 and 255 characters")
    private String medicineName;

    @NotBlank(message = "Dosage is required")
    @Size(min = 1, max = 100, message = "Dosage must be between 1 and 100 characters")
    private String dosage;

    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @Size(max = 500, message = "Instructions cannot exceed 500 characters")
    private String instructions;
}
