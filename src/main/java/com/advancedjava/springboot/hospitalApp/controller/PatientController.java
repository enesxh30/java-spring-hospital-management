package com.advancedjava.springboot.hospitalApp.controller;
import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.dto.PatientDto;
import com.advancedjava.springboot.hospitalApp.entity.Patient;
import com.advancedjava.springboot.hospitalApp.services.PatientService;
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
public class PatientController {

    private PatientService patientService;


    @GetMapping("/patients")
    public List<PatientDto> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/patients/{patientId}")
    public PatientDto findById(@PathVariable int patientId) {
        return patientService.findById(patientId);
    }

    @PostMapping("/patients/save")
    public PatientDto addPatient( @RequestBody PatientDto patientDto) {
        return patientService.save(patientDto);
    }

    @PutMapping("/patients/update/{patientId}")
    public PatientDto updatePatient(@PathVariable int patientId, @RequestBody PatientDto patientDto ){
        return patientService.update(patientDto,patientId);
    }

    @DeleteMapping("/patients/{patientId}")
    public ResponseEntity<Map<String, String>> deletePatient(@PathVariable int patientId) {
        patientService.delete(patientId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Patient with id " + patientId + " deleted successfully");
        return ResponseEntity.ok(response);
    }

}
