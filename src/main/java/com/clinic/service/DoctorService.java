package com.clinic.service;

import com.clinic.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    List<DoctorDTO> findAll();

    DoctorDTO findById(Long id);

    DoctorDTO create(DoctorDTO dto);

    DoctorDTO update(Long id, DoctorDTO dto);

    void delete(Long id);
}
