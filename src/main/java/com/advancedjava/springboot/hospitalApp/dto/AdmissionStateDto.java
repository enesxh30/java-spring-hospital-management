package com.advancedjava.springboot.hospitalApp.dto;

import com.advancedjava.springboot.hospitalApp.entity.ClinicalData;
import com.advancedjava.springboot.hospitalApp.entity.Departament;
import com.advancedjava.springboot.hospitalApp.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AdmissionStateDto {

    private Integer id;
    private LocalDate enteringDate;
    private LocalDate exitingDate;
    private String reason;
    private String cause;
    private boolean discharge;
    private Integer patientId;
    private Integer departmentId;

}
