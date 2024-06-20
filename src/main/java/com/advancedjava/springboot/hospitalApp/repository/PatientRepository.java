package com.advancedjava.springboot.hospitalApp.repository;

import com.advancedjava.springboot.hospitalApp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
