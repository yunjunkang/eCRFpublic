package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Hospital;
import com.evertri.ecrf.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // Get all hospitals
    @GetMapping("/")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    // Get a specific hospital by its ID number
    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable("id") Long id) {
        Hospital hospital = hospitalService.getHospitalById(id);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    // Save a new or updated hospital to the database
    @PostMapping("/")
    public ResponseEntity<Void> saveHospital(@RequestBody Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete a hospital from the database
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable("id") Long id) {
        hospitalService.deleteHospitalById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
