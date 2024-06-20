package com.advancedjava.springboot.hospitalApp.repository;

import com.advancedjava.springboot.hospitalApp.entity.AdmissionState;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionStateRepository extends JpaRepository<AdmissionState,Integer> {
}
