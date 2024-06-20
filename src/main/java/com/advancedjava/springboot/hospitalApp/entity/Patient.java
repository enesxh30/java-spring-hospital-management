package com.advancedjava.springboot.hospitalApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "patient")
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name",length = 100)
    private String name;
    @Column(name="lastName",length = 100)
    private String lastName;
    @Column(name = "birthDate")
    private LocalDate birthDate;


    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<AdmissionState> admissionStates;



}
