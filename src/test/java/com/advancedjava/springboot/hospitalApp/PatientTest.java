package com.advancedjava.springboot.hospitalApp;

import com.advancedjava.springboot.hospitalApp.controller.PatientController;
import com.advancedjava.springboot.hospitalApp.dto.PatientDto;
import com.advancedjava.springboot.hospitalApp.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PatientTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        PatientDto patient1 = new PatientDto();
        patient1.setId(1);
        patient1.setName("John");
        patient1.setLastName("Doe");
        patient1.setBirthDate(LocalDate.of(1990, 1, 1));

        PatientDto patient2 = new PatientDto();
        patient2.setId(2);
        patient2.setName("Jane");
        patient2.setLastName("Doe");
        patient2.setBirthDate(LocalDate.of(1992, 2, 2));

        List<PatientDto> patients = Arrays.asList(patient1, patient2);
        when(patientService.findAll()).thenReturn(patients);

        List<PatientDto> result = patientController.findAll();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Jane", result.get(1).getName());
        verify(patientService, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        PatientDto patient = new PatientDto();
        patient.setId(1);
        patient.setName("John");
        patient.setLastName("Doe");
        patient.setBirthDate(LocalDate.of(1990, 1, 1));

        when(patientService.findById(1)).thenReturn(patient);

        PatientDto result = patientController.findById(1);

        assertEquals(1, result.getId());
        assertEquals("John", result.getName());
        assertEquals("Doe", result.getLastName());
        verify(patientService, times(1)).findById(1);
    }

    @Test
    public void testAddPatient() {
        PatientDto patient = new PatientDto();
        patient.setId(1);
        patient.setName("John");
        patient.setLastName("Doe");
        patient.setBirthDate(LocalDate.of(1990, 1, 1));

        when(patientService.save(any(PatientDto.class))).thenReturn(patient);

        PatientDto result = patientController.addPatient(patient);

        assertEquals(1, result.getId());
        assertEquals("John", result.getName());
        assertEquals("Doe", result.getLastName());
        verify(patientService, times(1)).save(patient);
    }

    @Test
    public void testUpdatePatient() {
        PatientDto patient = new PatientDto();
        patient.setId(1);
        patient.setName("John");
        patient.setLastName("Doe");
        patient.setBirthDate(LocalDate.of(1990, 1, 1));

        when(patientService.update(any(PatientDto.class), eq(1))).thenReturn(patient);

        PatientDto result = patientController.updatePatient(1, patient);

        assertEquals(1, result.getId());
        assertEquals("John", result.getName());
        assertEquals("Doe", result.getLastName());
        verify(patientService, times(1)).update(patient, 1);
    }

    @Test
    public void testDeletePatient() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Patient with id 1 deleted successfully");

        ResponseEntity<Map<String, String>> result = patientController.deletePatient(1);

        assertEquals(response, result.getBody());
        verify(patientService, times(1)).delete(1);
    }
}
