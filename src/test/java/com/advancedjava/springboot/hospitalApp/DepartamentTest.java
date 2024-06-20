package com.advancedjava.springboot.hospitalApp;

import com.advancedjava.springboot.hospitalApp.controller.DepartamentController;
import com.advancedjava.springboot.hospitalApp.dto.DepartamentDto;
import com.advancedjava.springboot.hospitalApp.services.DepartamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartamentTest {

    @InjectMocks
    private DepartamentController departamentController;

    @Mock
    private DepartamentService departamentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        DepartamentDto departament1 = new DepartamentDto();
        departament1.setId(1);
        departament1.setName("Cardiology");
        departament1.setCode("CARD");

        DepartamentDto departament2 = new DepartamentDto();
        departament2.setId(2);
        departament2.setName("Neurology");
        departament2.setCode("NEURO");

        List<DepartamentDto> departaments = Arrays.asList(departament1, departament2);
        when(departamentService.findAll()).thenReturn(departaments);

        List<DepartamentDto> result = departamentController.findAll();

        assertEquals(2, result.size());
        assertEquals("Cardiology", result.get(0).getName());
        assertEquals("Neurology", result.get(1).getName());
        verify(departamentService, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        DepartamentDto departament = new DepartamentDto();
        departament.setId(1);
        departament.setName("Cardiology");
        departament.setCode("CARD");

        when(departamentService.findById(1)).thenReturn(departament);

        DepartamentDto result = departamentController.findById(1);

        assertEquals(1, result.getId());
        assertEquals("Cardiology", result.getName());
        assertEquals("CARD", result.getCode());
        verify(departamentService, times(1)).findById(1);
    }

    @Test
    public void testAddDepartament() {
        DepartamentDto departament = new DepartamentDto();
        departament.setId(1);
        departament.setName("Cardiology");
        departament.setCode("CARD");

        when(departamentService.save(any(DepartamentDto.class))).thenReturn(departament);

        DepartamentDto result = departamentController.addDepartament(departament);

        assertEquals(1, result.getId());
        assertEquals("Cardiology", result.getName());
        assertEquals("CARD", result.getCode());
        verify(departamentService, times(1)).save(departament);
    }

    @Test
    public void testUpdateDepartament() {
        DepartamentDto departament = new DepartamentDto();
        departament.setId(1);
        departament.setName("Cardiology");
        departament.setCode("CARD");

        when(departamentService.update(any(DepartamentDto.class), eq(1))).thenReturn(departament);

        DepartamentDto result = departamentController.updateDepartament(1, departament);

        assertEquals(1, result.getId());
        assertEquals("Cardiology", result.getName());
        assertEquals("CARD", result.getCode());
        verify(departamentService, times(1)).update(departament, 1);
    }

    @Test
    public void testDeleteDepartament() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Department with id 1 deleted successfully");

        ResponseEntity<Map<String, String>> result = departamentController.deleteDepartament(1);

        assertEquals(response, result.getBody());
        verify(departamentService, times(1)).delete(1);
    }
}
