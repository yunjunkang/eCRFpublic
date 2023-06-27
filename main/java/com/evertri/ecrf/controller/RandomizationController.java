package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Randomization;
import com.evertri.ecrf.service.RandomizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/randomization")
public class RandomizationController {
    private final RandomizationService randomizationService;

    @Autowired
    public RandomizationController(RandomizationService randomizationService) {
        this.randomizationService = randomizationService;
    }

    @GetMapping("/{trialId}")
    public ResponseEntity<Randomization> getRandomizationByTrialId(@PathVariable Long trialId) {
        Randomization randomization = randomizationService.getRandomizationByTrialId(trialId);
        if (randomization != null) {
            return ResponseEntity.ok(randomization);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/control/{control}")
    public ResponseEntity<List<Randomization>> getRandomizationByControl(@PathVariable String control) {
        List<Randomization> randomizations = randomizationService.getRandomizationByControl(control);
        if (!randomizations.isEmpty()) {
            return ResponseEntity.ok(randomizations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/test-group/{testGroup}")
    public ResponseEntity<List<Randomization>> getRandomizationByTestGroup(@PathVariable String testGroup) {
        List<Randomization> randomizations = randomizationService.getRandomizationByTestGroup(testGroup);
        if (!randomizations.isEmpty()) {
            return ResponseEntity.ok(randomizations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Add more controller methods here based on your requirements
}
