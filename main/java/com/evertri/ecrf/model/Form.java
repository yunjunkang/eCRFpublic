package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @Column(name = "user_id")
    private Long userId;

    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<Question> questions;

    // default constructor
    public Form() {
        this.questions = new ArrayList<>();
    }

    // constructor with name
    public Form(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.creationDate = LocalDate.now();
    }

    // getter and setter for id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // getter and setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // getter and setter for description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // getter and setter for creationDate
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    // getter and setter for the Study instance
    public Study getStudy() {
        return study;
    }
    public void setStudy(Study study) {
        this.study = study;
    }
    //getter and setter methods for userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Add getters and setters for the 'status' field
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // getter and setter for questions list
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // custom method to add a question to the form
    public void addQuestion(Question question) {
        questions.add(question);
        question.setForm(this);
    }

    // custom method to remove a question from the form
    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setForm(null);
    }
}
