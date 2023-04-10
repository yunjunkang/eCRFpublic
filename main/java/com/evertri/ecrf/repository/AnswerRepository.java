package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for Answer model
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    /**
     * Method to find answer by its id
     * @param id answer id
     * @return answer object
     */
    Answer getAnswerById(long id);

    /**
     * Method to find all answers for a given question
     * @param questionId question id
     * @return list of answers
     */
    @Query("SELECT a FROM Answer a WHERE a.question.id = ?1")
    List<Answer> findAllByQuestionId(long questionId);

    /**
     * Method to find all answers for a given form
     * @param formId form id
     * @return list of answers
     */
    @Query("SELECT a FROM Answer a WHERE a.question.form.id = ?1")
    List<Answer> findAllByFormId(long formId);

    /**
     * Method to find all answers for a given form and question
     * @param formId form id
     * @param questionId question id
     * @return list of answers
     */
    @Query("SELECT a FROM Answer a WHERE a.question.form.id = ?1 AND a.question.id = ?2")
    List<Answer> findAllByFormIdAndQuestionId(long formId, long questionId);

    /**
     * Method to find the number of answers for a given question
     * @param questionId question id
     * @return number of answers
     */
    @Query("SELECT COUNT(a) FROM Answer a WHERE a.question.id = ?1")
    long countByQuestionId(long questionId);

    /**
     * Method to find the number of answers for a given form
     * @param formId form id
     * @return number of answers
     */
    @Query("SELECT COUNT(a) FROM Answer a WHERE a.question.form.id = ?1")
    long countByFormId(long formId);

    /**
     * Method to find the number of answers for a given form and question
     * @param formId form id
     * @param questionId question id
     * @return number of answers
     */
    @Query("SELECT COUNT(a) FROM Answer a WHERE a.question.form.id = ?1 AND a.question.id = ?2")
    long countByFormIdAndQuestionId(long formId, long questionId);

    @Query("SELECT a FROM Answer a WHERE a.question.id = ?1")
    List<Answer> getAllAnswersByQuestionId(long questionId);

    /**
     * Method to find all answers for a given form and patient
     *
     * @param formId form id
     * @param patientId patient id
     * @return list of answers
     */
    @Query("SELECT a FROM Answer a WHERE a.form.id = ?1 AND a.patient.id = ?2")
    List<Answer> getAllAnswersByFormIdAndPatientId(long formId, long patientId);

}
