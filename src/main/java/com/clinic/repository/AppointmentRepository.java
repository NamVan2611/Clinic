package com.clinic.repository;

import com.clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND CAST(a.appointmentTime AS date) = :date ORDER BY a.appointmentTime")
    List<Appointment> findAppointmentsByDoctorIdAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND CAST(a.appointmentTime AS date) = CURRENT_DATE ORDER BY a.appointmentTime")
    List<Appointment> findTodayAppointmentsForDoctor(@Param("doctorId") Long doctorId);
}
