package com.advancedjava.springboot.hospitalApp.services;

import com.advancedjava.springboot.hospitalApp.dto.ClinicalDataDto;
import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.entity.AdmissionState;
import com.advancedjava.springboot.hospitalApp.entity.Departament;
import com.advancedjava.springboot.hospitalApp.mapper.ClinicalDataMapper;
import com.advancedjava.springboot.hospitalApp.repository.AdmissionStateRepository;
import com.advancedjava.springboot.hospitalApp.repository.ClinicDataRepository;
import com.advancedjava.springboot.hospitalApp.entity.ClinicalData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClinicalDataService{

    private ClinicDataRepository clinicDataRepository;
    private ClinicalDataMapper clinicalDataMapper;
    private AdmissionStateRepository admissionStateRepository;


    public List<ClinicalDataDto> findAll() {
        List<ClinicalData> clinicalDataList = clinicDataRepository.findAll();
        List<ClinicalDataDto> clinicalDataDtoList = new ArrayList<>();
        for (ClinicalData clinicalData: clinicalDataList){
            clinicalDataDtoList.add(clinicalDataMapper.mapToDto(clinicalData));
        }
        return clinicalDataDtoList;
    }

    public ClinicalDataDto findById(int clinicalId) {
        ClinicalData existingData = clinicDataRepository.findById(clinicalId)
                .orElseThrow( ()-> new RuntimeException("Clinical Data with id: " + clinicalId+ " was not found"));

        return clinicalDataMapper.mapToDto(existingData);
    }

    public ClinicalDataDto save(ClinicalDataDto clinicalDataDto) {

        ClinicalData clinicalData  = clinicalDataMapper.mapToEntity(clinicalDataDto);


        if (clinicalDataDto.getAdmissionStateId() != null) {
            AdmissionState admissionState = admissionStateRepository.findById(clinicalDataDto.getAdmissionStateId())
                    .orElseThrow(() -> new RuntimeException("AdmissionState with id: " + clinicalDataDto.getAdmissionStateId() + " was not found"));
            clinicalData.setAdmissionState(admissionState);
        } else {
            clinicalData.setAdmissionState(null);
        }

        ClinicalData savedData = clinicDataRepository.save(clinicalData);

        return clinicalDataMapper.mapToDto(savedData);
    }

    public void delete(int clinicalId) {
        Optional<ClinicalData> existingData = clinicDataRepository.findById(clinicalId);

        if (existingData.isPresent()){
            clinicDataRepository.delete(existingData.get());
        }else {throw new RuntimeException("Clinical Data not found with ID: " + clinicalId);}

    }
    public ClinicalDataDto update(ClinicalDataDto clinicalDataDto,int clinicId){
        ClinicalData existingData = clinicDataRepository.findById(clinicId).orElseThrow(() ->
                new RuntimeException("Clinical Data not found with ID: " + clinicId));
        existingData.setId(clinicId);
        existingData.setClinicalRecord(clinicalDataDto.getClinicalRecord());

        if (clinicalDataDto.getAdmissionStateId() != null) {
            AdmissionState admissionState = admissionStateRepository.findById(clinicalDataDto.getAdmissionStateId())
                    .orElseThrow(() -> new RuntimeException("AdmissionState with id: " + clinicalDataDto.getAdmissionStateId() + " was not found"));
            existingData.setAdmissionState(admissionState);
        } else {
            existingData.setAdmissionState(null);
        }

        if (clinicalDataDto.getAdmissionStateId() != null) {
            existingData.getAdmissionState().setId(clinicalDataDto.getAdmissionStateId());
        }

        ClinicalData savedData = clinicDataRepository.save(existingData);
        return clinicalDataMapper.mapToDto(savedData);

    }
}
