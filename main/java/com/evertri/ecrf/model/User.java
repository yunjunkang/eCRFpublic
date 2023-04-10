package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_study", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "study_id"))
    private List<Study> studies;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_hospital", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "hospital_id"))
    private Set<Hospital> hospitals;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .flatMap(role -> role.getAuthorities().stream())
                .collect(Collectors.toSet());
    }


    //default constructor
    public User() {
        studies = new ArrayList<>();
        roles = new ArrayList<>(); // Initialize the roles list
    }


    //constructor with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        studies = new ArrayList<>(); // Initialize the studies list
    }

    //getter and setter for id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //getter and setter for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    //getter and setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //getter and setter for studies list
    public List<Study> getStudies() {
        return studies;
    }
    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    //custom method to add a study to the user's studies list

    public void addStudy(Study study) {
        studies.add(study);
        study.getUsers().add(this);
    }

    //custom method to remove a study from the user's studies list
    public void removeStudy(Study study) {
        studies.remove(study);
        study.getUsers().remove(this);
    }


    // Getter and setter for roles list
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    // Custom method to check if the user has authorization to view a specific study
    public boolean hasAuthorization(Long studyId) {
        if (studies == null) {
            return false;
        }
        return studies.stream().anyMatch(study -> study.getId().equals(studyId));
    }

    // Getter and setter for hospitals
    public Set<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(Set<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
