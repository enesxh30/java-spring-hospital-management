package com.advancedjava.springboot.hospitalApp.mapper;
import com.advancedjava.springboot.hospitalApp.dto.PatientDto;
import com.advancedjava.springboot.hospitalApp.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientMapper {
    public Patient mapToEntity(PatientDto patientDto){
        Patient patient = new Patient();
//        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setLastName(patientDto.getLastName());
        patient.setBirthDate(patientDto.getBirthDate());

        return patient;
    }
    public PatientDto mapToDto(Patient patient){
        PatientDto patientDto  = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setBirthDate(patient.getBirthDate());

        return patientDto;
    }
}
