package com.clinic.service.impl;

import com.clinic.dto.PrescriptionDTO;
import com.clinic.dto.PrescriptionItemDTO;
import com.clinic.entity.*;
import com.clinic.mapper.PrescriptionItemMapper;
import com.clinic.mapper.PrescriptionMapper;
import com.clinic.repository.AppointmentRepository;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.PatientRepository;
import com.clinic.repository.PrescriptionRepository;
import com.clinic.exception.BusinessException;
import com.clinic.service.PrescriptionService;
import com.clinic.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final PrescriptionItemMapper prescriptionItemMapper;

    @Override
    @Transactional
    public PrescriptionDTO create(PrescriptionDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + dto.getAppointmentId()));

        if (prescriptionRepository.existsByAppointmentId(dto.getAppointmentId())) {
            throw new BusinessException("A prescription already exists for this appointment.");
        }

        Prescription prescription = Prescription.builder()
                .doctor(doctor)
                .patient(patient)
                .appointment(appointment)
                .notes(dto.getNotes())
                .build();

        List<PrescriptionItem> items = new ArrayList<>();
        if (dto.getItems() != null) {
            for (PrescriptionItemDTO itemDTO : dto.getItems()) {
                PrescriptionItem item = prescriptionItemMapper.toEntity(itemDTO);
                item.setPrescription(prescription);
                items.add(item);
            }
        }
        prescription.setItems(items);

        Prescription saved = prescriptionRepository.save(prescription);

        appendToPatientMedicalHistory(patient, saved);

        return prescriptionMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PrescriptionDTO findById(Long id) {
        return prescriptionRepository.findById(id)
                .map(prescriptionMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrescriptionDTO> findByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientIdOrderByCreatedAtDesc(patientId).stream()
                .map(prescriptionMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrescriptionDTO> findByAppointmentId(Long appointmentId) {
        return prescriptionRepository.findByAppointmentId(appointmentId).stream()
                .map(prescriptionMapper::toDTO)
                .toList();
    }

    private void appendToPatientMedicalHistory(Patient patient, Prescription prescription) {
        StringBuilder historyEntry = new StringBuilder();
        if (patient.getMedicalHistory() != null && !patient.getMedicalHistory().isBlank()) {
            historyEntry.append(patient.getMedicalHistory()).append("\n\n");
        }
        historyEntry.append("--- Prescription #").append(prescription.getId())
                .append(" (").append(prescription.getCreatedAt()).append(") ---\n");
        historyEntry.append("Notes: ").append(prescription.getNotes() != null ? prescription.getNotes() : "-").append("\n");
        for (PrescriptionItem item : prescription.getItems()) {
            historyEntry.append("- ").append(item.getMedicineName())
                    .append(": ").append(item.getDosage() != null ? item.getDosage() : "")
                    .append(", qty ").append(item.getQuantity() != null ? item.getQuantity() : "")
                    .append(" - ").append(item.getInstructions() != null ? item.getInstructions() : "").append("\n");
        }
        patient.setMedicalHistory(historyEntry.toString());
        patientRepository.save(patient);
    }
}
