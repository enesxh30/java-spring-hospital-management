package com.advancedjava.springboot.hospitalApp.controller;

import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.entity.Departament;
import com.advancedjava.springboot.hospitalApp.services.DepartamentService;
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
public class DepartamentController {
    private DepartamentService departamentService;

    @GetMapping("/departaments")
    public List<DepartamentDto> findAll() {
        return departamentService.findAll();
    }

    @GetMapping("/departaments/{departamentId}")
    public DepartamentDto findById(@PathVariable int departamentId) {
        return departamentService.findById(departamentId);
    }

    @PostMapping("/departaments/save")
    public DepartamentDto addDepartament( @RequestBody DepartamentDto departamentDto) {
        return departamentService.save(departamentDto);
    }

    @PutMapping("/departaments/update/{departamentId}")
    public DepartamentDto updateDepartament(@PathVariable int departamentId, @RequestBody DepartamentDto departamentDto ){
        return departamentService.update(departamentDto,departamentId);
    }

    @DeleteMapping("/departaments/{departamentId}")
    public ResponseEntity<Map<String, String>> deleteDepartament(@PathVariable int departamentId) {
        departamentService.delete(departamentId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Department with id " + departamentId + " deleted successfully");
        return ResponseEntity.ok(response);
    }

}
