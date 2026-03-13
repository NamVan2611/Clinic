package com.clinic.service;

import com.clinic.dto.PrescriptionDTO;

import java.util.List;

public interface PrescriptionService {

    PrescriptionDTO create(PrescriptionDTO dto);

    PrescriptionDTO findById(Long id);

    List<PrescriptionDTO> findByPatientId(Long patientId);

    List<PrescriptionDTO> findByAppointmentId(Long appointmentId);
}
