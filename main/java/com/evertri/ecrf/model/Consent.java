package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "consent")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @Column(name = "signature_path")
    private String signaturePath;

    @Column(name = "date_signed")
    private LocalDateTime dateSigned;


    public Consent() {}

    public Consent(Patient patient, Study study, String signaturePath) {
        this.patient = patient;
        this.study = study;
        this.signaturePath = signaturePath;
        this.dateSigned = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public String getSignaturePath() {
        return signaturePath;
    }

    public void setSignaturePath(String signaturePath) {
        this.signaturePath = signaturePath;
    }

    public LocalDateTime getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(LocalDateTime dateSigned) {
        this.dateSigned = dateSigned;
    }
}
