package com.advancedjava.springboot.hospitalApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
 public class Departament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "name",nullable = false, length = 100)
    private String name;

    @Column(name="code",columnDefinition = "TEXT")
    private String code;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<AdmissionState> admissionStates;

}