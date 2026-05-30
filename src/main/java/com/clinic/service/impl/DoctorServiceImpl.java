package com.clinic.service.impl;

import com.clinic.dto.DoctorDTO;
import com.clinic.entity.Doctor;
import com.clinic.mapper.DoctorMapper;
import com.clinic.repository.DoctorRepository;
import com.clinic.service.DoctorService;
import com.clinic.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DoctorDTO> findAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorDTO findById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    @Transactional
    public DoctorDTO create(DoctorDTO dto) {
        Doctor entity = doctorMapper.toEntity(dto);
        Doctor saved = doctorRepository.save(entity);
        return doctorMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public DoctorDTO update(Long id, DoctorDTO dto) {
        Doctor entity = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        entity.setName(dto.getName());
        entity.setSpecialization(dto.getSpecialization());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        return doctorMapper.toDTO(doctorRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Doctor entity = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        entity.delete();
        doctorRepository.save(entity);
    }
}
