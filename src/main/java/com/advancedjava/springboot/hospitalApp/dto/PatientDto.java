package com.advancedjava.springboot.hospitalApp.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class PatientDto {

    private int id;
    private String name;
    private String lastName;
    private LocalDate birthDate;

}
