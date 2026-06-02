package com.clinic.mapper;

import com.clinic.dto.AppointmentDTO;
import com.clinic.entity.Appointment;
import com.clinic.entity.Doctor;
import com.clinic.entity.Patient;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-01T23:38:03+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260528-0407, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDTO toDTO(Appointment entity) {
        if ( entity == null ) {
            return null;
        }

        AppointmentDTO.AppointmentDTOBuilder appointmentDTO = AppointmentDTO.builder();

        appointmentDTO.patientId( entityPatientId( entity ) );
        appointmentDTO.doctorId( entityDoctorId( entity ) );
        appointmentDTO.appointmentTime( entity.getAppointmentTime() );
        appointmentDTO.id( entity.getId() );
        appointmentDTO.room( entity.getRoom() );
        appointmentDTO.status( entity.getStatus() );

        return appointmentDTO.build();
    }

    @Override
    public Appointment toEntity(AppointmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointment = Appointment.builder();

        appointment.appointmentTime( dto.getAppointmentTime() );
        appointment.id( dto.getId() );
        appointment.room( dto.getRoom() );
        appointment.status( dto.getStatus() );

        return appointment.build();
    }

    private Long entityPatientId(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Patient patient = appointment.getPatient();
        if ( patient == null ) {
            return null;
        }
        Long id = patient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityDoctorId(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Doctor doctor = appointment.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        Long id = doctor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
