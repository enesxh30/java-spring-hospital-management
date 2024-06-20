package com.advancedjava.springboot.hospitalApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admissionstate")
public class AdmissionState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name="enteringDate")
    private LocalDate enteringDate;

    @Temporal(TemporalType.DATE)
    @Column(name="exitingDate")
    private LocalDate exitingDate;

    @Column(name="reason",length = 45)
    private String reason;

    @Column(name="cause",length = 100)
    private String cause;

    @Column(name = "discharge")
    private boolean discharge;

    @OneToMany(mappedBy = "admissionState", cascade = CascadeType.ALL)
    private List<ClinicalData> clinicalDataList;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true)
    private Departament department;


}
