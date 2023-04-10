package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "study")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Form> forms;



    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_study", joinColumns = @JoinColumn(name = "study_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "study_hospital", joinColumns = @JoinColumn(name = "study_id"), inverseJoinColumns = @JoinColumn(name = "hospital_id"))
    private List<Hospital> hospitals = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Patient> patients;


    @Column(name = "status")
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "creation_date", columnDefinition = "timestamp")
    private LocalDateTime creationDate;

    public Study() {
        this.creationDate = LocalDateTime.now();
        this.forms = new ArrayList<>();
        this.patients = new ArrayList<>(); // Add this line
    }

    public Study(String name) {
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.forms = new ArrayList<>();
        this.patients = new ArrayList<>(); // Add this line
    }

    public Study(String name, String description) {
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.forms = new ArrayList<>();
        this.patients = new ArrayList<>(); // Add this line
    }

    public Study(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.creationDate = LocalDateTime.now();
        this.forms = new ArrayList<>();
        this.patients = new ArrayList<>(); // Add this line
    }

    // getter and setter methods
    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addForm(Form form) {
        forms.add(form);
        form.setStudy(this);
    }

    public void removeForm(Form form) {
        forms.remove(form);
        form.setStudy(null);
    }

    public void addUser(User user) {
        users.add(user);
        user.getStudies().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getStudies().remove(this);
    }

    // Add getter and setter for creationDate
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
