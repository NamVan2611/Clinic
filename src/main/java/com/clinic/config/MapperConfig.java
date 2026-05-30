package com.clinic.config;

import com.clinic.mapper.AppointmentMapper;
import com.clinic.mapper.DoctorMapper;
import com.clinic.mapper.PatientMapper;
import com.clinic.mapper.PrescriptionItemMapper;
import com.clinic.mapper.PrescriptionMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public AppointmentMapper appointmentMapper() {
        return Mappers.getMapper(AppointmentMapper.class);
    }

    @Bean
    public DoctorMapper doctorMapper() {
        return Mappers.getMapper(DoctorMapper.class);
    }

    @Bean
    public PatientMapper patientMapper() {
        return Mappers.getMapper(PatientMapper.class);
    }

    @Bean
    public PrescriptionMapper prescriptionMapper() {
        return Mappers.getMapper(PrescriptionMapper.class);
    }

    @Bean
    public PrescriptionItemMapper prescriptionItemMapper() {
        return Mappers.getMapper(PrescriptionItemMapper.class);
    }
}
