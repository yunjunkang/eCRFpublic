package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Visit;
import com.evertri.ecrf.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        return new ResponseEntity<>(visitService.getAllVisits(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id) {
        Optional<Visit> visit = visitService.getVisitById(id);
        return visit.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        return new ResponseEntity<>(visitService.saveOrUpdateVisit(visit), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        visitService.getVisitById(id).orElseThrow(() -> new RuntimeException("Visit not found"));
        visit.setId(id);
        return new ResponseEntity<>(visitService.saveOrUpdateVisit(visit), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        visitService.deleteVisitById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

