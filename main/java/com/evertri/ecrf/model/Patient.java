package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "medical_history")
    private String medicalHistory;


    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Consent> consents;



    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;


    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Visit> visits;



    //default constructor
    public Patient() {
        this.consents = new ArrayList<>();
        this.study = new Study();
    }

    //constructor with name and date of birth
    public Patient(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.consents = new ArrayList<>();
        this.study = new Study();
    }

    //getter and setter for id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //getter and setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //getter and setter for date of birth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //getter and setter for nationalId
    public String getNationalId() {
        return nationalId;
    }
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    //getter and setter for gender
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    //getter and setter for medical history
    public String getMedicalHistory() {
        return medicalHistory;
    }
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    //getter and setter for consents list
    public List<Consent> getConsents() {
        return consents;
    }
    public void setConsents(List<Consent> consents) {
        this.consents = consents;
    }

    //custom method to add a consent to the patient's consents list
    public void addConsent(Consent consent) {
        consents.add(consent);
        consent.setPatient(this);
    }

    //custom method to remove a consent from the patient's consents list
    public void removeConsent(Consent consent) {
        consents.remove(consent);
        consent.setPatient(null);
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    // Add the following getter and setter methods
    public Set<Visit> getVisits() {
        return visits;
    }
    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setPatient(this);
    }
    public void removeVisit(Visit visit) {
        visits.remove(visit);
        visit.setPatient(null);
    }
}
