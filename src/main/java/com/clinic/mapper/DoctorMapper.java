package com.clinic.mapper;

import com.clinic.dto.DoctorDTO;
import com.clinic.entity.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDTO toDTO(Doctor entity);

    Doctor toEntity(DoctorDTO dto);
}
