package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Hospital;
import com.evertri.ecrf.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }


    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    public void deleteHospitalById(Long id) {
        hospitalRepository.deleteById(id);
    }

    public Hospital updateHospital(Long id, Hospital hospital) {
        Hospital existingHospital = hospitalRepository.findById(id).orElse(null);
        if (existingHospital == null) {
            return null;
        }
        hospital.setId(id);
        return hospitalRepository.save(hospital);
    }


}

