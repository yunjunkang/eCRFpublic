package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Answer;
import com.evertri.ecrf.model.Option;
import com.evertri.ecrf.model.*;
import com.evertri.ecrf.model.Question;
import com.evertri.ecrf.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for the Answer model
 */
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    /**
     * Method to save a new answer
     *
     * @param answer answer to be saved
     * @return saved answer
     */
    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    /**
     * Method to find an answer by its id
     *
     * @param id answer id
     * @return answer object
     */
    public Answer getAnswerById(long id) {
        return answerRepository.getAnswerById(id);
    }

    /**
     * Method to find all answers for a given form
     *
     * @param formId form id
     * @return list of answers
     */
    public List<Answer> getAllAnswersByFormId(long formId) {
        return answerRepository.findAllByFormId(formId);
    }

    /**
     * Method to find all answers for a given question
     *
     * @param questionId question id
     * @return list of answers
     */
    public List<Answer> findAllByQuestionId(long questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }

    /**
     * Method to find all answers for a given form and question
     *
     * @param formId     form id
     * @param questionId question id
     * @return list of answers
     */
    public List<Answer> findAllByFormIdAndQuestionId(long formId, long questionId) {
        return answerRepository.findAllByFormIdAndQuestionId(formId, questionId);
    }

    /**
     * Method to find the number of answers for a given question
     *
     * @param questionId question id
     * @return number of answers
     */
    public long countByQuestionId(long questionId) {
        return answerRepository.countByQuestionId(questionId);
    }

    /**
     * Method to find the number of answers for a given form
     *
     * @param formId form id
     * @return number of answers
     */
    public long countByFormId(long formId) {
        return answerRepository.countByFormId(formId);
    }

    /**
     * Method to find the number of answers for a given form and question
     *
     * @param formId     form id
     * @param questionId question id
     * @return number of answers
     */
    public long countByFormIdAndQuestionId(long formId, long questionId) {
        return answerRepository.countByFormIdAndQuestionId(formId, questionId);
    }


    /**
     * Method to retrieve all answers from the database
     *
     * @return list of answers
     */
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    /**
     * Method to update an answer in the database
     *
     * @param id
     * @param answer updated answer object
     * @return updated answer object
     */
    public Answer updateAnswer(Long id, Answer answer) {
        return answerRepository.save(answer);
    }

    /**
     * Method to delete an answer from the database by its id
     *
     * @param id answer id
     */
    public void deleteAnswerById(long id) {
        answerRepository.deleteById(id);
    }

    /**
     * Method to find all answers related to a specific form
     *
     * @param formId form id to find answers for
     * @return list of answers related to the form
     */
    public List<Answer> findAllAnswersByFormId(Long formId) {
        return answerRepository.findAllByFormId(formId);
    }

    /**
     * Method to find all answers for a given question
     *
     * @param questionId question id
     * @return list of answers
     */
    public List<Answer> getAllAnswersByQuestionId(long questionId) {
        return answerRepository.getAllAnswersByQuestionId(questionId);
    }

    public List<Answer> getAllAnswersByFormIdAndPatientId(Long formId, Long patientId) {
        // Assuming you have a method in your AnswerRepository to handle this query
        return answerRepository.getAllAnswersByFormIdAndPatientId(formId, patientId);
    }

    public Answer savePickAllThatApplyAnswer(PickAllThatApplyAnswer pickAllThatApplyAnswer(Question question, List<Option> options, String otherValue)) {
        return answerRepository.save(pickAllThatApplyAnswer);
    }

    public Answer saveMultipleChoiceAnswer(Answer.MultipleChoiceAnswer multipleChoiceAnswer) {
        return answerRepository.save(multipleChoiceAnswer);
    }


}

