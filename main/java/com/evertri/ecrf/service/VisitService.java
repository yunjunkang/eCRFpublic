package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Visit;
import com.evertri.ecrf.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit saveOrUpdateVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public void deleteVisitById(Long id) {
        visitRepository.deleteById(id);
    }
}
