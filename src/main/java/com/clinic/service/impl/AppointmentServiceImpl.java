package com.clinic.service.impl;

import com.clinic.dto.AppointmentDTO;
import com.clinic.entity.Appointment;
import com.clinic.entity.AppointmentStatus;
import com.clinic.entity.Doctor;
import com.clinic.entity.Patient;
import com.clinic.exception.BusinessException;
import com.clinic.mapper.AppointmentMapper;
import com.clinic.repository.AppointmentRepository;
import com.clinic.repository.DoctorRepository;
import com.clinic.repository.PatientRepository;
import com.clinic.service.AppointmentService;
import com.clinic.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final int APPOINTMENT_DURATION_MINUTES = 30;

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentDTO findById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    @Override
    @Transactional
    public AppointmentDTO create(AppointmentDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));

        validateDoctorAvailability(doctor.getId(), dto.getAppointmentTime(), null);

        Appointment entity = appointmentMapper.toEntity(dto);
        entity.setPatient(patient);
        entity.setDoctor(doctor);
        Appointment saved = appointmentRepository.save(entity);
        return appointmentMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public AppointmentDTO update(Long id, AppointmentDTO dto) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));

        validateDoctorAvailability(doctor.getId(), dto.getAppointmentTime(), id);

        entity.setPatient(patient);
        entity.setDoctor(doctor);
        entity.setAppointmentTime(dto.getAppointmentTime());
        entity.setRoom(dto.getRoom());
        entity.setStatus(dto.getStatus());
        Appointment saved = appointmentRepository.save(entity);
        return appointmentMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId).stream()
                .map(appointmentMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findByDoctorIdAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findAppointmentsByDoctorIdAndDate(doctorId, date).stream()
                .map(appointmentMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findTodayAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findTodayAppointmentsForDoctor(doctorId).stream()
                .map(appointmentMapper::toDTO)
                .toList();
    }

    private void validateDoctorAvailability(Long doctorId, LocalDateTime appointmentTime, Long excludeAppointmentId) {
        LocalDate date = appointmentTime.toLocalDate();
        List<Appointment> existingAppointments = appointmentRepository.findAppointmentsByDoctorIdAndDate(doctorId, date);

        LocalDateTime newStart = appointmentTime.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime newEnd = newStart.plusMinutes(APPOINTMENT_DURATION_MINUTES);

        boolean hasOverlap = existingAppointments.stream()
                .filter(a -> excludeAppointmentId == null || !a.getId().equals(excludeAppointmentId))
                .filter(a -> a.getStatus() != AppointmentStatus.CANCELLED && a.getStatus() != AppointmentStatus.NO_SHOW)
                .anyMatch(a -> {
                    LocalDateTime existingStart = a.getAppointmentTime().truncatedTo(ChronoUnit.MINUTES);
                    LocalDateTime existingEnd = existingStart.plusMinutes(APPOINTMENT_DURATION_MINUTES);
                    return newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);
                });

        if (hasOverlap) {
            throw new BusinessException("Doctor has an overlapping appointment at the requested time. Please choose a different slot.");
        }
    }
}
