package com.advancedjava.springboot.hospitalApp.services;

import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.mapper.DepartamentMapper;
import com.advancedjava.springboot.hospitalApp.repository.DepartamentRepository;
import com.advancedjava.springboot.hospitalApp.entity.Departament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartamentService {

    private DepartamentRepository departamentRepository;
    private DepartamentMapper departamentMapper;


    public DepartamentDto save(DepartamentDto departamentDto){
        Departament departament = departamentMapper.mapToEntity(departamentDto);
        Departament savedDepartment = departamentRepository.save(departament);

        return departamentMapper.mapToDto(savedDepartment);
    }

    public List<DepartamentDto> findAll(){
        List<Departament> departamentList = departamentRepository.findAll();
        List<DepartamentDto> departamentDtoList = new ArrayList<>();
        for (Departament departament: departamentList){
            departamentDtoList.add(departamentMapper.mapToDto(departament));
        }
        return departamentDtoList;

    }

    public DepartamentDto findById(int departmentId){
        Departament existingDepartment = departamentRepository.findById(departmentId)
                .orElseThrow( ()-> new RuntimeException("Department with id: " + departmentId+ " was not found"));

        return departamentMapper.mapToDto(existingDepartment);
    }

    public DepartamentDto update(DepartamentDto departamentDto,int departmentId){
        Departament existingDepartament = departamentRepository.findById(departmentId).orElseThrow(() ->
                new RuntimeException("Departament not found with ID: " + departmentId));
        existingDepartament.setId(departmentId);
        existingDepartament.setName(departamentDto.getName());
        existingDepartament.setCode(departamentDto.getCode());

        Departament savedDepartament = departamentRepository.save(existingDepartament);
        return departamentMapper.mapToDto(savedDepartament);

    }
    public void delete(int departamentId) {
        Optional<Departament> existingDepartament = departamentRepository.findById(departamentId);

        if (existingDepartament.isPresent()){
            departamentRepository.delete(existingDepartament.get());
        }else {throw new RuntimeException("Departament not found with ID: " + departamentId);}
    }
}
