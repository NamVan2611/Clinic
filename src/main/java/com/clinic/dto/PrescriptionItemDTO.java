package com.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemDTO {

    private Long id;

    private Long prescriptionId;

    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    private String dosage;

    private Integer quantity;

    private String instructions;
}
