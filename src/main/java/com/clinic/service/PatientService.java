package com.clinic.service;

import com.clinic.dto.PatientDTO;
import com.clinic.dto.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

    List<PatientDTO> findAll();

    PageResponse<PatientDTO> findAll(Pageable pageable);

    PatientDTO findById(Long id);

    PatientDTO create(PatientDTO dto);

    PatientDTO update(Long id, PatientDTO dto);

    void delete(Long id);
}
