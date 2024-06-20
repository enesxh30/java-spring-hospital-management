package com.advancedjava.springboot.hospitalApp.services;

import com.advancedjava.springboot.hospitalApp.dto.AdmissionStateDto;
import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.entity.Departament;
import com.advancedjava.springboot.hospitalApp.entity.Patient;
import com.advancedjava.springboot.hospitalApp.mapper.AdmissionStateMapper;
import com.advancedjava.springboot.hospitalApp.repository.AdmissionStateRepository;
import com.advancedjava.springboot.hospitalApp.entity.AdmissionState;
import com.advancedjava.springboot.hospitalApp.repository.DepartamentRepository;
import com.advancedjava.springboot.hospitalApp.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdmissionStateService{

    private AdmissionStateRepository admissionStateRepository;
    private AdmissionStateMapper admissionStateMapper;
    private PatientRepository patientRepository;
    private DepartamentRepository departamentRepository;

    public AdmissionStateDto save(AdmissionStateDto admissionStateDto){
        AdmissionState admissionState = admissionStateMapper.mapToEntity(admissionStateDto);

        Optional<Patient> foundPatient = patientRepository.findById(admissionStateDto.getPatientId());
        if(foundPatient.isPresent()){
            admissionState.setPatient(foundPatient.get());
        }

        if (admissionStateDto.getDepartmentId() != null) {
            Optional<Departament> foundDepartament = departamentRepository.findById(admissionStateDto.getDepartmentId());
            if (foundDepartament.isPresent()) {
                admissionState.setDepartment(foundDepartament.get());
            } else {
                throw new RuntimeException("Department not found with ID: " + admissionStateDto.getDepartmentId());
            }
        } else {
            admissionState.setDepartment(null);
        }

        AdmissionState savedAdmissionState = admissionStateRepository.save(admissionState);
        return admissionStateMapper.mapToDto(savedAdmissionState);
    }

    public List<AdmissionStateDto> findAll(){
        List<AdmissionState> admissionStateList = admissionStateRepository.findAll();
        List<AdmissionStateDto> admissionStateDtoList = new ArrayList<>();
        for (AdmissionState admissionState: admissionStateList){
            admissionStateDtoList.add(admissionStateMapper.mapToDto(admissionState));
        }
        return admissionStateDtoList;
//        return studentList.stream().map(student -> studentMapper.mapToDto(student)).collect(Collectors.toList());

    }

    public AdmissionStateDto findById(int admissionStateId){
        AdmissionState existingAdmissionState = admissionStateRepository.findById(admissionStateId)
                .orElseThrow( ()-> new RuntimeException("AdmissionState with id: " + admissionStateId+ " was not found"));

        return admissionStateMapper.mapToDto(existingAdmissionState);
    }

    public AdmissionStateDto update(AdmissionStateDto admissionStateDto,int admissionStateId){
        AdmissionState existingAdmission = admissionStateRepository.findById(admissionStateId).orElseThrow(() ->
                new RuntimeException("Admission not found with ID: " + admissionStateId));

        if (admissionStateDto.getDepartmentId() != null) {
            Optional<Departament> foundDepartament = departamentRepository.findById(admissionStateDto.getDepartmentId());
            if (foundDepartament.isPresent()) {
                existingAdmission.setDepartment(foundDepartament.get());
            } else {
                throw new RuntimeException("Department not found with ID: " + admissionStateDto.getDepartmentId());
            }
        } else {
            existingAdmission.setDepartment(null);
        }
        if (admissionStateDto.getPatientId() != null) {
            Optional<Patient> foundPatient = patientRepository.findById(admissionStateDto.getPatientId());
            if (foundPatient.isPresent()) {
                existingAdmission.setPatient(foundPatient.get());
            } else {
                throw new RuntimeException("Patient not found with ID: " + admissionStateDto.getPatientId());
            }
        } else {
            existingAdmission.setPatient(null);
        }


        existingAdmission.setId(admissionStateId);
        existingAdmission.setReason(admissionStateDto.getReason());
        System.out.println("----------------------------------  "+admissionStateDto.isDischarge()); //boolean value test
        existingAdmission.setDischarge(admissionStateDto.isDischarge()); // boolean
        existingAdmission.setCause(admissionStateDto.getCause());
        existingAdmission.setEnteringDate(admissionStateDto.getEnteringDate());
        existingAdmission.setExitingDate(admissionStateDto.getExitingDate());

        if (admissionStateDto.getDepartmentId() != null){
            existingAdmission.getDepartment().setId(admissionStateDto.getDepartmentId());
        }
        if (admissionStateDto.getPatientId() != null){
            existingAdmission.getPatient().setId(admissionStateDto.getPatientId());
        }

        AdmissionState savedAdmissionState = admissionStateRepository.save(existingAdmission);
        return admissionStateMapper.mapToDto(savedAdmissionState);

    }
    public void delete(int admissionId) {
        Optional<AdmissionState> existingAdmission = admissionStateRepository.findById(admissionId);

        if (existingAdmission.isPresent()){
            admissionStateRepository.delete(existingAdmission.get());
        }else {throw new RuntimeException("Admission not found with ID: " + admissionId);}
    }
}

