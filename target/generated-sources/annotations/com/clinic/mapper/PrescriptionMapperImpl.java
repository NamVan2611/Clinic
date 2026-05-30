package com.clinic.mapper;

import com.clinic.dto.PrescriptionDTO;
import com.clinic.dto.PrescriptionItemDTO;
import com.clinic.entity.Appointment;
import com.clinic.entity.Doctor;
import com.clinic.entity.Patient;
import com.clinic.entity.Prescription;
import com.clinic.entity.PrescriptionItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-30T12:10:40+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260512-1158, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class PrescriptionMapperImpl implements PrescriptionMapper {

    @Autowired
    private PrescriptionItemMapper prescriptionItemMapper;

    @Override
    public PrescriptionDTO toDTO(Prescription entity) {
        if ( entity == null ) {
            return null;
        }

        PrescriptionDTO.PrescriptionDTOBuilder prescriptionDTO = PrescriptionDTO.builder();

        prescriptionDTO.doctorId( entityDoctorId( entity ) );
        prescriptionDTO.patientId( entityPatientId( entity ) );
        prescriptionDTO.appointmentId( entityAppointmentId( entity ) );
        prescriptionDTO.createdAt( entity.getCreatedAt() );
        prescriptionDTO.id( entity.getId() );
        prescriptionDTO.items( prescriptionItemListToPrescriptionItemDTOList( entity.getItems() ) );
        prescriptionDTO.notes( entity.getNotes() );

        return prescriptionDTO.build();
    }

    @Override
    public Prescription toEntity(PrescriptionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Prescription.PrescriptionBuilder prescription = Prescription.builder();

        prescription.id( dto.getId() );
        prescription.notes( dto.getNotes() );

        return prescription.build();
    }

    private Long entityDoctorId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Doctor doctor = prescription.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        Long id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityPatientId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Patient patient = prescription.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityAppointmentId(Prescription prescription) {
        if ( prescription == null ) {
            return null;
        }
        Appointment appointment = prescription.getAppointment();
        if ( appointment == null ) {
            return null;
        }
        Long id = appointment.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<PrescriptionItemDTO> prescriptionItemListToPrescriptionItemDTOList(List<PrescriptionItem> list) {
        if ( list == null ) {
            return null;
        }

        List<PrescriptionItemDTO> list1 = new ArrayList<PrescriptionItemDTO>( list.size() );
        for ( PrescriptionItem prescriptionItem : list ) {
            list1.add( prescriptionItemMapper.toDTO( prescriptionItem ) );
        }

        return list1;
    }
}
