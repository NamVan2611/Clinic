package com.clinic.controller;

import com.clinic.dto.AppointmentDTO;
import com.clinic.service.AppointmentService;
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
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "Appointment management APIs")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    @Operation(summary = "Get all appointments")
    public ResponseEntity<List<AppointmentDTO>> getAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PostMapping
    @Operation(summary = "Create a new appointment")
    public ResponseEntity<AppointmentDTO> create(@Valid @RequestBody AppointmentDTO dto) {
        log.info("Creating appointment for patient {} with doctor {}", dto.getPatientId(), dto.getDoctorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.create(dto));
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get today's appointments for a doctor")
    public ResponseEntity<List<AppointmentDTO>> getByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.findTodayAppointmentsForDoctor(doctorId));
    }
}
