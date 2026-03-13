package com.clinic.controller;

import com.clinic.dto.PrescriptionDTO;
import com.clinic.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Prescriptions", description = "Prescription management APIs")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get prescriptions by patient ID")
    public ResponseEntity<List<PrescriptionDTO>> getByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(prescriptionService.findByPatientId(patientId));
    }

    @PostMapping
    @Operation(summary = "Create a new prescription")
    public ResponseEntity<PrescriptionDTO> create(@Valid @RequestBody PrescriptionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionService.create(dto));
    }
}
