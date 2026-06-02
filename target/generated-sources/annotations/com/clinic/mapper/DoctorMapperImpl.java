package com.clinic.mapper;

import com.clinic.dto.DoctorDTO;
import com.clinic.entity.Doctor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-01T23:38:04+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260528-0407, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO toDTO(Doctor entity) {
        if ( entity == null ) {
            return null;
        }

        DoctorDTO.DoctorDTOBuilder doctorDTO = DoctorDTO.builder();

        doctorDTO.email( entity.getEmail() );
        doctorDTO.id( entity.getId() );
        doctorDTO.name( entity.getName() );
        doctorDTO.phone( entity.getPhone() );
        doctorDTO.specialization( entity.getSpecialization() );

        return doctorDTO.build();
    }

    @Override
    public Doctor toEntity(DoctorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Doctor.DoctorBuilder doctor = Doctor.builder();

        doctor.email( dto.getEmail() );
        doctor.id( dto.getId() );
        doctor.name( dto.getName() );
        doctor.phone( dto.getPhone() );
        doctor.specialization( dto.getSpecialization() );

        return doctor.build();
    }
}
