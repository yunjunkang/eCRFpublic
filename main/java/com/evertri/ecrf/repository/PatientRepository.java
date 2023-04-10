package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Find all patients in a specific study.
     */
    @Query("SELECT p FROM Patient p WHERE p.study.id = :studyId")
    List<Patient> findByStudyId(@Param("studyId") Long studyId);

    /**
     * Find all patients with a specific name.
     */
    List<Patient> findByName(String name);

    /**
     * Find a patient by their ID number.
     */
    Patient findByNationalId(String nationalId);

    /**
     * Find all patients in a specific age range.
     */
    @Query(value = "SELECT * FROM patient p WHERE TIMESTAMPDIFF(YEAR, p.date_of_birth, CURRENT_DATE) BETWEEN :minAge AND :maxAge", nativeQuery = true)
    List<Patient> findByAgeBetween(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    /**
     * Find all patients with a specific gender.
     */
    List<Patient> findByGender(String gender);

    /**
     * Find all patients with a specific medical history.
     */
    List<Patient> findByMedicalHistory(String medicalHistory);

    /**
     * Find all patients with a specific consent signature path.
     */
    @Query("SELECT p FROM Patient p JOIN p.consents c WHERE c.signaturePath = :signaturePath")
    List<Patient> findByConsentSignaturePath(@Param("signaturePath") String signaturePath);

}

