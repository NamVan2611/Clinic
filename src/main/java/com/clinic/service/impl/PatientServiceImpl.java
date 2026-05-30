package com.clinic.service.impl;

import com.clinic.dto.PatientDTO;
import com.clinic.dto.PageResponse;
import com.clinic.entity.Patient;
import com.clinic.mapper.PatientMapper;
import com.clinic.repository.PatientRepository;
import com.clinic.service.PatientService;
import com.clinic.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<PatientDTO> findAll(Pageable pageable) {
        Page<Patient> page = patientRepository.findAll(pageable);
        List<PatientDTO> content = page.getContent().stream()
                .map(patientMapper::toDTO)
                .toList();
        return PageResponse.<PatientDTO>builder()
                .content(content)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    @Override
    @Transactional
    public PatientDTO create(PatientDTO dto) {
        Patient entity = patientMapper.toEntity(dto);
        Patient saved = patientRepository.save(entity);
        return patientMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public PatientDTO update(Long id, PatientDTO dto) {
        Patient entity = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        entity.setName(dto.getName());
        entity.setDob(dto.getDob());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setMedicalHistory(dto.getMedicalHistory());
        return patientMapper.toDTO(patientRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Patient entity = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        entity.delete();
        patientRepository.save(entity);
    }
}
