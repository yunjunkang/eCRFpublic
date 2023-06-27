package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Randomization;
import com.evertri.ecrf.repository.RandomizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomizationService {
    private final RandomizationRepository randomizationRepository;

    @Autowired
    public RandomizationService(RandomizationRepository randomizationRepository) {
        this.randomizationRepository = randomizationRepository;
    }

    public Randomization getRandomizationByTrialId(Long trialId) {
        return randomizationRepository.findByTrialId(trialId);
    }


    // Service method to get randomization by control
    public List<Randomization> getRandomizationByControl(String control) {
        return randomizationRepository.findByControl(control);
    }

    // Service method to get randomization by test group
    public List<Randomization> getRandomizationByTestGroup(String testGroup) {
        return randomizationRepository.findByTestGroup(testGroup);
    }

    // Add more service methods here based on your requirements
}
