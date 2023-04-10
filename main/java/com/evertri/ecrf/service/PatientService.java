package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Patient;
import com.evertri.ecrf.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Retrieve a list of all patients in the database
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Retrieve a specific patient by their ID number
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    // Retrieve a list of patients in a specific study
    public List<Patient> getPatientsByStudyId(Long studyId) {
        return patientRepository.findByStudyId(studyId);
    }

    // Retrieve a list of patients with a specific name
    public List<Patient> getPatientsByName(String name) {
        return patientRepository.findByName(name);
    }

    // Retrieve a patient by their national ID number
    public Patient getPatientByNationalId(String nationalId) {
        return patientRepository.findByNationalId(nationalId);
    }

    // Retrieve a list of patients in a specific age range
    public List<Patient> getPatientsByAgeRange(int minAge, int maxAge) {
        return patientRepository.findByAgeBetween(minAge, maxAge);
    }

    // Retrieve a list of patients with a specific gender
    public List<Patient> getPatientsByGender(String gender) {
        return patientRepository.findByGender(gender);
    }

    // Retrieve a list of patients with a specific medical history
    public List<Patient> getPatientsByMedicalHistory(String medicalHistory) {
        return patientRepository.findByMedicalHistory(medicalHistory);
    }

    // Save a new or updated patient to the database
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    // Delete a patient from the database
    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }
}
