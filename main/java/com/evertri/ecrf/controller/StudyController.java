package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Study;
import com.evertri.ecrf.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studies")
public class StudyController {

    private final StudyService studyService;

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    // Get all studies
    @GetMapping("/")
    public ResponseEntity<List<Study>> getAllStudies() {
        List<Study> studies = studyService.getAllStudies();
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    // Get a specific study by its ID number
    @GetMapping("/{id}")
    public ResponseEntity<Study> getStudyById(@PathVariable("id") Long id) {
        Study study = studyService.getStudyById(id);
        return new ResponseEntity<>(study, HttpStatus.OK);
    }

    // Save a new or updated study to the database
    @PostMapping("/")
    public ResponseEntity<Void> saveStudy(@RequestBody Study study) {
        studyService.saveStudy(study);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Delete a study from the database
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable("id") Long id) {
        studyService.deleteStudyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
