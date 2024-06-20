package com.advancedjava.springboot.hospitalApp;

import com.advancedjava.springboot.hospitalApp.controller.AdmissionStateController;
import com.advancedjava.springboot.hospitalApp.dto.AdmissionStateDto;
import com.advancedjava.springboot.hospitalApp.services.AdmissionStateService;
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

public class AdmissionStateControllerTest {

    @InjectMocks
    private AdmissionStateController admissionStateController;

    @Mock
    private AdmissionStateService admissionStateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        AdmissionStateDto admission1 = new AdmissionStateDto();
        admission1.setId(1);
        admission1.setReason("Reason 1");
        admission1.setCause("Cause 1");

        AdmissionStateDto admission2 = new AdmissionStateDto();
        admission2.setId(2);
        admission2.setReason("Reason 2");
        admission2.setCause("Cause 2");

        List<AdmissionStateDto> admissions = Arrays.asList(admission1, admission2);
        when(admissionStateService.findAll()).thenReturn(admissions);

        List<AdmissionStateDto> result = admissionStateController.findAll();

        assertEquals(2, result.size());
        assertEquals("Reason 1", result.get(0).getReason());
        assertEquals("Reason 2", result.get(1).getReason());
        verify(admissionStateService, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        AdmissionStateDto admission = new AdmissionStateDto();
        admission.setId(1);
        admission.setReason("Reason 1");
        admission.setCause("Cause 1");

        when(admissionStateService.findById(1)).thenReturn(admission);

        AdmissionStateDto result = admissionStateController.findById(1);

        assertEquals(1, result.getId());
        assertEquals("Reason 1", result.getReason());
        assertEquals("Cause 1", result.getCause());
        verify(admissionStateService, times(1)).findById(1);
    }

    @Test
    public void testAddAdmission() {
        AdmissionStateDto admission = new AdmissionStateDto();
        admission.setId(1);
        admission.setReason("Reason 1");
        admission.setCause("Cause 1");
        admission.setEnteringDate(LocalDate.of(2023, 1, 1));
        admission.setExitingDate(LocalDate.of(2023, 1, 10));

        when(admissionStateService.save(any(AdmissionStateDto.class))).thenReturn(admission);

        AdmissionStateDto result = admissionStateController.addAdmission(admission);

        assertEquals(1, result.getId());
        assertEquals("Reason 1", result.getReason());
        assertEquals("Cause 1", result.getCause());
        verify(admissionStateService, times(1)).save(admission);
    }

    @Test
    public void testUpdateAdmission() {
        AdmissionStateDto admission = new AdmissionStateDto();
        admission.setId(1);
        admission.setReason("Reason 1");
        admission.setCause("Cause 1");
        admission.setEnteringDate(LocalDate.of(2023, 1, 1));
        admission.setExitingDate(LocalDate.of(2023, 1, 10));

        when(admissionStateService.update(any(AdmissionStateDto.class), eq(1))).thenReturn(admission);

        AdmissionStateDto result = admissionStateController.updateAdmission(admission, 1);

        assertEquals(1, result.getId());
        assertEquals("Reason 1", result.getReason());
        assertEquals("Cause 1", result.getCause());
        verify(admissionStateService, times(1)).update(admission, 1);
    }

    @Test
    public void testDeleteAdmission() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Admission with id 1 deleted successfully");

        ResponseEntity<Map<String, String>> result = admissionStateController.deleteAdmission(1);

        assertEquals(response, result.getBody());
        verify(admissionStateService, times(1)).delete(1);
    }
}
