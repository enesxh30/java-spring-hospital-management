package com.advancedjava.springboot.hospitalApp.mapper;

import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.entity.Departament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DepartamentMapper {


    public Departament mapToEntity(DepartamentDto departamentDto){
        Departament departament = new Departament();
//        departament.setId(departamentDto.getId());
        departament.setName(departamentDto.getName());

        return departament;
    }
    public DepartamentDto mapToDto(Departament departament){
        DepartamentDto departamentDto = new DepartamentDto();
        departamentDto.setId(departament.getId());
        departamentDto.setName(departament.getName());
        departamentDto.setCode(departament.getCode());


        return departamentDto;
    }

}
