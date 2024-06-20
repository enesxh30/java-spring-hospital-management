package com.advancedjava.springboot.hospitalApp.mapper;

import com.advancedjava.springboot.hospitalApp.dto.AdmissionStateDto;
import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.dto.PatientDto;
import com.advancedjava.springboot.hospitalApp.entity.AdmissionState;
import com.advancedjava.springboot.hospitalApp.entity.Departament;
import com.advancedjava.springboot.hospitalApp.entity.Patient;
import com.advancedjava.springboot.hospitalApp.services.DepartamentService;
import com.advancedjava.springboot.hospitalApp.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class AdmissionStateMapper {

    public AdmissionState mapToEntity(AdmissionStateDto admissionStateDto){
        AdmissionState admissionState = new AdmissionState();
        admissionState.setId(admissionStateDto.getId());
        admissionState.setCause(admissionStateDto.getCause());
        admissionState.setEnteringDate(admissionStateDto.getEnteringDate());
        admissionState.setExitingDate(admissionStateDto.getExitingDate());
        admissionState.setReason(admissionStateDto.getReason());
        admissionState.setDischarge(admissionState.isDischarge());


        return admissionState;
    }
    public AdmissionStateDto mapToDto(AdmissionState admissionState){

        AdmissionStateDto admissionStateDto = new AdmissionStateDto();
        admissionStateDto.setId(admissionState.getId());
        admissionStateDto.setDischarge(admissionStateDto.isDischarge());
        admissionStateDto.setReason(admissionState.getReason());
        admissionStateDto.setCause(admissionState.getCause());

        if (admissionState.getPatient() != null) {
            admissionStateDto.setPatientId(admissionState.getPatient().getId());
        }
        if (admissionState.getDepartment() != null) {
            admissionStateDto.setDepartmentId(admissionState.getDepartment().getId());
        }
        admissionStateDto.setEnteringDate(admissionState.getEnteringDate());
        admissionStateDto.setExitingDate(admissionState.getExitingDate());

        return admissionStateDto;
    }
}
