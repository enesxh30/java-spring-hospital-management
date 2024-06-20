package com.advancedjava.springboot.hospitalApp.repository;

import com.advancedjava.springboot.hospitalApp.entity.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicDataRepository extends JpaRepository<ClinicalData,Integer> {
}
