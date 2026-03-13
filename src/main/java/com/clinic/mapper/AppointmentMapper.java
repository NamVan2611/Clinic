package com.clinic.mapper;

import com.clinic.dto.AppointmentDTO;
import com.clinic.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "doctor.id", target = "doctorId")
    AppointmentDTO toDTO(Appointment entity);

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "prescription", ignore = true)
    Appointment toEntity(AppointmentDTO dto);
}
