package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Study;
import com.evertri.ecrf.repository.StudyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    /**
     * Method to find all studies
     * @return List of studies
     */
    public List<Study> getAllStudies() {
        return studyRepository.findAll();
    }

    /**
     * Method to create a new study
     * @param study study object
     * @return created study object
     */
    public Study create(Study study) {
        return studyRepository.save(study);
    }

    /**
     * Method to save a study
     * @param study study object
     * @return saved study object
     */
    public Study saveStudy(Study study) {
        return studyRepository.save(study);
    }

    /**
     * Method to find a study by its id
     *
     * @param id study id
     * @return study object
     */
    public Study getStudyById(Long id) {
        return studyRepository.findById(id).orElse(null);
    }

    /**
     * Method to delete a study by its id
     * @param id study id
     */
    public void deleteStudyById(Long id) {
        studyRepository.deleteById(id);
    }

    /**
     * Method to update a study
     * @param id study id
     * @param study updated study object
     * @return updated study object
     */
    public Study updateStudy(Long id, Study study) {
        Study existingStudy = studyRepository.findById(id).orElse(null);
        if (existingStudy == null) {
            return null;
        }
        study.setId(id);
        return studyRepository.save(study);
    }
}
