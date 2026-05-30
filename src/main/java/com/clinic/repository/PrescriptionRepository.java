package com.clinic.repository;

import com.clinic.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT p FROM Prescription p WHERE p.patient.id = :patientId AND p.isDeleted = false ORDER BY p.createdAt DESC")
    List<Prescription> findByPatientIdOrderByCreatedAtDesc(@Param("patientId") Long patientId);

    @Query("SELECT p FROM Prescription p WHERE p.appointment.id = :appointmentId AND p.isDeleted = false")
    List<Prescription> findByAppointmentId(@Param("appointmentId") Long appointmentId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Prescription p WHERE p.appointment.id = :appointmentId AND p.isDeleted = false")
    boolean existsByAppointmentId(@Param("appointmentId") Long appointmentId);

    @Query("SELECT p FROM Prescription p WHERE p.id = :id AND p.isDeleted = false")
    Optional<Prescription> findById(@Param("id") Long id);

    @Query("SELECT p FROM Prescription p WHERE p.isDeleted = false")
    List<Prescription> findAll();
}
