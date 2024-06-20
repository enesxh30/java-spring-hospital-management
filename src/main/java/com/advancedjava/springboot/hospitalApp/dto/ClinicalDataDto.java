package com.advancedjava.springboot.hospitalApp.dto;

import com.advancedjava.springboot.hospitalApp.entity.AdmissionState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicalDataDto {
    private Integer id;
    private String clinicalRecord;
    private Integer admissionStateId;
}
