package com.clinic.repository;

import com.clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT d FROM Doctor d WHERE d.id = :id AND d.isDeleted = false")
    Optional<Doctor> findById(@Param("id") Long id);

    @Query("SELECT d FROM Doctor d WHERE d.isDeleted = false")
    List<Doctor> findAll();
}
