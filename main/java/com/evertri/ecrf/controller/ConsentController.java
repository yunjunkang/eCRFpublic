package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Consent;
import com.evertri.ecrf.service.ConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consents")
public class ConsentController {

    private final ConsentService consentService;

    @Autowired
    public ConsentController(ConsentService consentService) {
        this.consentService = consentService;
    }

    // Get a specific consent by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Consent> getConsentById(@PathVariable("id") Long id) {
        Consent consent = consentService.findById(id);
        return consent != null ? ResponseEntity.ok(consent) : ResponseEntity.notFound().build();
    }

    // Create a new consent
    @PostMapping("/")
    public ResponseEntity<Consent> saveConsent(@RequestBody Consent consent) {
        Consent createdConsent = consentService.create(consent);
        return new ResponseEntity<>(createdConsent, HttpStatus.CREATED);
    }

    // Update an existing consent by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Consent> updateConsent(@PathVariable("id") Long id, @RequestBody Consent consent) {
        Consent updatedConsent = consentService.update(id, consent);
        return updatedConsent != null ? ResponseEntity.ok(updatedConsent) : ResponseEntity.notFound().build();
    }

    // Delete a consent by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsentById(@PathVariable("id") Long id) {
        consentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
