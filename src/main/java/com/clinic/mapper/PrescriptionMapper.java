package com.clinic.mapper;

import com.clinic.dto.PrescriptionDTO;
import com.clinic.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PrescriptionItemMapper.class)
public interface PrescriptionMapper {

    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "appointment.id", target = "appointmentId")
    PrescriptionDTO toDTO(Prescription entity);

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "items", ignore = true)
    Prescription toEntity(PrescriptionDTO dto);
}
