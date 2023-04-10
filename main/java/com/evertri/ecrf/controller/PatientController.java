package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Patient;
import com.evertri.ecrf.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Get a list of all patients in the database
    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a specific patient by their ID number
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        Patient patient = patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Get a list of patients in a specific study
    @GetMapping("/study/{studyId}")
    public ResponseEntity<List<Patient>> getPatientsByStudyId(@PathVariable("studyId") Long studyId) {
        List<Patient> patients = patientService.getPatientsByStudyId(studyId);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a list of patients with a specific name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Patient>> getPatientsByName(@PathVariable("name") String name) {
        List<Patient> patients = patientService.getPatientsByName(name);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a patient by their national ID number
    @GetMapping("/national-id/{nationalId}")
    public ResponseEntity<Patient> getPatientByNationalId(@PathVariable("nationalId") String nationalId) {
        Patient patient = patientService.getPatientByNationalId(nationalId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Get a list of patients in a specific age range
    @GetMapping("/age-range/{minAge}/{maxAge}")
    public ResponseEntity<List<Patient>> getPatientsByAgeRange(@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge) {
        List<Patient> patients = patientService.getPatientsByAgeRange(minAge, maxAge);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a list of patients with a specific gender
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Patient>> getPatientsByGender(@PathVariable("gender") String gender) {
        List<Patient> patients = patientService.getPatientsByGender(gender);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a list of patients with a specific medical history
    @GetMapping("/medical-history/{medicalHistory}")
    public ResponseEntity<List<Patient>> getPatientsByMedicalHistory(@PathVariable("medicalHistory") String medicalHistory) {
        List<Patient> patients = patientService.getPatientsByMedicalHistory(medicalHistory);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Save a new or updated patient to the database
    @PostMapping("/")
    public ResponseEntity<Void> savePatient(@RequestBody Patient patient) {
        patientService.savePatient(patient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete a patient from the database
    @DeleteMapping("/")
    public ResponseEntity<Void> deletePatient(@RequestBody Patient patient) {
        patientService.deletePatient(patient);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
