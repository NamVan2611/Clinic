package com.clinic.repository;

import com.clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.isDeleted = false")
    List<Appointment> findByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND CAST(a.appointmentTime AS date) = :date AND a.isDeleted = false ORDER BY a.appointmentTime")
    List<Appointment> findAppointmentsByDoctorIdAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND CAST(a.appointmentTime AS date) = CURRENT_DATE AND a.isDeleted = false ORDER BY a.appointmentTime")
    List<Appointment> findTodayAppointmentsForDoctor(@Param("doctorId") Long doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.id = :id AND a.isDeleted = false")
    Optional<Appointment> findById(@Param("id") Long id);

    @Query("SELECT a FROM Appointment a WHERE a.isDeleted = false")
    List<Appointment> findAll();
}
