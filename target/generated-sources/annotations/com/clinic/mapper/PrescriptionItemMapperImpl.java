package com.clinic.mapper;

import com.clinic.dto.PrescriptionItemDTO;
import com.clinic.entity.Prescription;
import com.clinic.entity.PrescriptionItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-30T12:10:40+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260512-1158, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class PrescriptionItemMapperImpl implements PrescriptionItemMapper {

    @Override
    public PrescriptionItemDTO toDTO(PrescriptionItem entity) {
        if ( entity == null ) {
            return null;
        }

        PrescriptionItemDTO.PrescriptionItemDTOBuilder prescriptionItemDTO = PrescriptionItemDTO.builder();

        prescriptionItemDTO.prescriptionId( entityPrescriptionId( entity ) );
        prescriptionItemDTO.dosage( entity.getDosage() );
        prescriptionItemDTO.id( entity.getId() );
        prescriptionItemDTO.instructions( entity.getInstructions() );
        prescriptionItemDTO.medicineName( entity.getMedicineName() );
        prescriptionItemDTO.quantity( entity.getQuantity() );

        return prescriptionItemDTO.build();
    }

    @Override
    public PrescriptionItem toEntity(PrescriptionItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PrescriptionItem.PrescriptionItemBuilder prescriptionItem = PrescriptionItem.builder();

        prescriptionItem.dosage( dto.getDosage() );
        prescriptionItem.id( dto.getId() );
        prescriptionItem.instructions( dto.getInstructions() );
        prescriptionItem.medicineName( dto.getMedicineName() );
        prescriptionItem.quantity( dto.getQuantity() );

        return prescriptionItem.build();
    }

    private Long entityPrescriptionId(PrescriptionItem prescriptionItem) {
        if ( prescriptionItem == null ) {
            return null;
        }
        Prescription prescription = prescriptionItem.getPrescription();
        if ( prescription == null ) {
            return null;
        }
        Long id = prescription.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
