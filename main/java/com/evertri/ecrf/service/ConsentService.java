package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Consent;
import com.evertri.ecrf.repository.ConsentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsentService {
    private ConsentRepository consentRepository;

    public ConsentService() {}

    public ConsentService(ConsentRepository consentRepository) {
        this.consentRepository = consentRepository;
    }

    /**
     * Method to find all consents
     * @return List of consents
     */
    public List<Consent> findAll() {
        return consentRepository.findAll();
    }

    /**
     * Method to create a new consent
     * @param consent consent object
     * @return created consent object
     */
    public Consent create(Consent consent) {
        return consentRepository.save(consent);
    }

    /**
     * Method to save a consent
     * @param consent consent object
     * @return saved consent object
     */
    public Consent save(Consent consent) {
        return consentRepository.save(consent);
    }

    /**
     * Method to find a consent by its id
     * @param id consent id
     * @return consent object
     */
    public Consent findById(Long id) {
        return consentRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete a consent by its id
     * @param id consent id
     */
    public void deleteById(Long id) {
        consentRepository.deleteById(id);
    }

    /**
     * Method to update a consent
     * @param id consent id
     * @param consent updated consent object
     * @return updated consent object
     */
    public Consent update(Long id, Consent consent) {
        Consent existingConsent = consentRepository.findById(id).orElse(null);
        if (existingConsent == null) {
            return null;
        }
        consent.setId(id);
        return consentRepository.save(consent);
    }

}
