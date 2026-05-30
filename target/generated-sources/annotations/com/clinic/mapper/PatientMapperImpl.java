package com.clinic.mapper;

import com.clinic.dto.PatientDTO;
import com.clinic.entity.Patient;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-30T12:10:40+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260512-1158, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toDTO(Patient entity) {
        if ( entity == null ) {
            return null;
        }

        PatientDTO.PatientDTOBuilder patientDTO = PatientDTO.builder();

        patientDTO.address( entity.getAddress() );
        patientDTO.createdAt( entity.getCreatedAt() );
        patientDTO.dob( entity.getDob() );
        patientDTO.email( entity.getEmail() );
        patientDTO.id( entity.getId() );
        patientDTO.medicalHistory( entity.getMedicalHistory() );
        patientDTO.name( entity.getName() );
        patientDTO.phone( entity.getPhone() );

        return patientDTO.build();
    }

    @Override
    public Patient toEntity(PatientDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Patient.PatientBuilder patient = Patient.builder();

        patient.address( dto.getAddress() );
        patient.dob( dto.getDob() );
        patient.email( dto.getEmail() );
        patient.id( dto.getId() );
        patient.medicalHistory( dto.getMedicalHistory() );
        patient.name( dto.getName() );
        patient.phone( dto.getPhone() );

        return patient.build();
    }
}
