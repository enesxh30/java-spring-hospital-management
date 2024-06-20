package com.advancedjava.springboot.hospitalApp.repository;

import com.advancedjava.springboot.hospitalApp.entity.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentRepository extends JpaRepository<Departament,Integer> {
}
