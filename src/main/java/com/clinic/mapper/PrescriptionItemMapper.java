package com.clinic.mapper;

import com.clinic.dto.PrescriptionItemDTO;
import com.clinic.entity.PrescriptionItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrescriptionItemMapper {

    @Mapping(source = "prescription.id", target = "prescriptionId")
    PrescriptionItemDTO toDTO(PrescriptionItem entity);

    @Mapping(target = "prescription", ignore = true)
    PrescriptionItem toEntity(PrescriptionItemDTO dto);
}
