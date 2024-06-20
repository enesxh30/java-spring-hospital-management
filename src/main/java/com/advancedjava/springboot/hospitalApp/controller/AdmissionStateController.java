package com.advancedjava.springboot.hospitalApp.controller;

import com.advancedjava.springboot.hospitalApp.dto.AdmissionStateDto;
import com.advancedjava.springboot.hospitalApp.dto.PatientDto;
import com.advancedjava.springboot.hospitalApp.entity.AdmissionState;
import com.advancedjava.springboot.hospitalApp.services.AdmissionStateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8100")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AdmissionStateController {

    private AdmissionStateService admissionService;

    @GetMapping("/admissions")
    public List<AdmissionStateDto> findAll(){
        return admissionService.findAll();
    }

    @GetMapping("/admissions/{admissionsId}")
    public AdmissionStateDto findById(@PathVariable int admissionsId) {
        return admissionService.findById(admissionsId);
    }
    @PostMapping("/admissions/save")
    public AdmissionStateDto addAdmission(@Valid @RequestBody AdmissionStateDto admissionStateDto){
        return admissionService.save(admissionStateDto);
    }
    @PutMapping("/admissions/update/{admissionsId}")
    public AdmissionStateDto updateAdmission(@Valid @RequestBody AdmissionStateDto admissionStateDto,@PathVariable int admissionsId){
        return admissionService.update(admissionStateDto,admissionsId);
    }
    @DeleteMapping("/admissions/{admissionsId}")
    public ResponseEntity<Map<String, String>> deleteAdmission(@PathVariable int admissionsId) {
        admissionService.delete(admissionsId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Admission with id " + admissionsId + " deleted successfully");
        return ResponseEntity.ok(response);
    }


}
