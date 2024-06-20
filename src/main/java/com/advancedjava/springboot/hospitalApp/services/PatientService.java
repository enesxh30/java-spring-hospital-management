package com.advancedjava.springboot.hospitalApp.services;

import com.advancedjava.springboot.hospitalApp.dto.PatientDto;
import com.advancedjava.springboot.hospitalApp.mapper.PatientMapper;
import com.advancedjava.springboot.hospitalApp.repository.PatientRepository;
import com.advancedjava.springboot.hospitalApp.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PatientService{

    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public PatientDto save(PatientDto patientDto){
        Patient patient = patientMapper.mapToEntity(patientDto);
        Patient savedPatient  = patientRepository.save(patient);

        return patientMapper.mapToDto(savedPatient);
    }

    public List<PatientDto> findAll(){
        List<Patient> patientList = patientRepository.findAll();
        List<PatientDto> patientDtoList = new ArrayList<>();
        for (Patient patient: patientList){
            patientDtoList.add(patientMapper.mapToDto(patient));
        }
        return patientDtoList;

    }

    public PatientDto findById(int patientId){
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow( ()-> new RuntimeException("Patient with id: " + patientId+ " was not found"));

        return patientMapper.mapToDto(existingPatient);
    }

    public PatientDto update(PatientDto patientDto,int patientId){
        Patient existingPatient = patientRepository.findById(patientId).orElseThrow(() ->
                new RuntimeException("Patient not found with ID: " + patientId));
        existingPatient.setId(patientId);
        existingPatient.setName(patientDto.getName());
        existingPatient.setLastName(patientDto.getLastName());
        existingPatient.setBirthDate(patientDto.getBirthDate());

        Patient savedPatient = patientRepository.save(existingPatient);
        return patientMapper.mapToDto(savedPatient);

    }
    public void delete(int patientId) {
        Optional<Patient> existingPatient = patientRepository.findById(patientId);

        if (existingPatient.isPresent()){
            patientRepository.delete(existingPatient.get());
        }else {throw new RuntimeException("Patient was not found with ID: " + patientId);}
    }


    }

