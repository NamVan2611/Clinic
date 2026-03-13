package com.clinic.service;

import com.clinic.dto.AppointmentDTO;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    List<AppointmentDTO> findAll();

    AppointmentDTO findById(Long id);

    AppointmentDTO create(AppointmentDTO dto);

    AppointmentDTO update(Long id, AppointmentDTO dto);

    void delete(Long id);

    List<AppointmentDTO> findByPatientId(Long patientId);

    List<AppointmentDTO> findByDoctorIdAndDate(Long doctorId, LocalDate date);

    List<AppointmentDTO> findTodayAppointmentsForDoctor(Long doctorId);
}
