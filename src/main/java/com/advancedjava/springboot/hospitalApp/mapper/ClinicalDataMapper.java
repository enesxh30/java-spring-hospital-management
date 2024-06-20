package com.advancedjava.springboot.hospitalApp.mapper;

import com.advancedjava.springboot.hospitalApp.dto.ClinicalDataDto;
import com.advancedjava.springboot.hospitalApp.entity.ClinicalData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClinicalDataMapper {
    public ClinicalData mapToEntity(ClinicalDataDto clinicalDataDto){
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setId(clinicalDataDto.getId());
        clinicalData.setClinicalRecord(clinicalDataDto.getClinicalRecord());


        return clinicalData;
    }
    public ClinicalDataDto mapToDto(ClinicalData clinicalData){
        ClinicalDataDto clinicalDataDto = new ClinicalDataDto();
        clinicalDataDto.setId(clinicalData.getId());
        clinicalDataDto.setClinicalRecord(clinicalData.getClinicalRecord());

        if (clinicalData.getAdmissionState() != null){
            clinicalDataDto.setAdmissionStateId(clinicalData.getAdmissionState().getId());
        }

        return clinicalDataDto;
    }

}
