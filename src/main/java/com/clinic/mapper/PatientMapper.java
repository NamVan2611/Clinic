package com.clinic.mapper;

import com.clinic.dto.PatientDTO;
import com.clinic.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDTO(Patient entity);

    Patient toEntity(PatientDTO dto);
}
