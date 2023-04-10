package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    // Default constructor for JPA
    public Dashboard() {
    }

    // Constructor with form
    public Dashboard(Form form) {
        this.form = form;
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for form
    public Form getForm() {
        return form;
    }

    // Setter for form
    public void setForm(Form form) {
        this.form = form;
    }

    // Method to generate different dashboards based on the form
    public void generateDashboard() {
        switch (form.getName()) {
            case "Form1":
                // Generate dashboard for Form1
                break;
            case "Form2":
                // Generate dashboard for Form2
                break;
            // And so on
        }
    }
}
