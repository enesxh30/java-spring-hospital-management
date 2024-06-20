package com.advancedjava.springboot.hospitalApp.controller;

import com.advancedjava.springboot.hospitalApp.dto.ClinicalDataDto;
import com.advancedjava.springboot.hospitalApp.entity.ClinicalData;
import com.advancedjava.springboot.hospitalApp.services.ClinicalDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8100")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ClinicalDataController {

    private ClinicalDataService clinicalService;

    @GetMapping("/clinicaldata")
    public List<ClinicalDataDto> findAll() {
        return clinicalService.findAll();
    }

    @GetMapping("/clinicaldata/{clinicId}")
    public ClinicalDataDto getClinicaldata(@PathVariable int clinicId) {
        return clinicalService.findById(clinicId);
    }

    @PostMapping("/clinicaldata")
    public ClinicalDataDto addClinicaldata(@RequestBody ClinicalDataDto clinicalDataDto) {
        return clinicalService.save(clinicalDataDto);
    }

    @PutMapping("/clinicaldata/update/{clinicId}")
    public ClinicalDataDto updateClinic(@PathVariable int clinicId,@RequestBody ClinicalDataDto clinicalDataDto) {
        return clinicalService.update(clinicalDataDto,clinicId);
    }
    @DeleteMapping("/clinicaldata/{clinicId}")
    public ResponseEntity<Map<String, String>> deleteClinic(@PathVariable int clinicId) {
        clinicalService.delete(clinicId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Clinical Data with id " + clinicId + " deleted successfully");
        return ResponseEntity.ok(response);
    }

}
