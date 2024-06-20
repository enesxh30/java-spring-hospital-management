package com.advancedjava.springboot.hospitalApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clinicaldata")
public class ClinicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "clinicalRecord", length = 255)
    private String clinicalRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admissionState_id", nullable = true)
    private AdmissionState admissionState;

}
