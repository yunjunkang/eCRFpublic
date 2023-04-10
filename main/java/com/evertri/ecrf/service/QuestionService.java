package com.evertri.ecrf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.evertri.ecrf.model.Question;
import com.evertri.ecrf.repository.QuestionRepository;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // Returns all questions
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    // Returns question by ID
    public Optional<Question> findQuestionById(Long questionId) {
        return questionRepository.findById(questionId);
    }

    // Creates new question
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Updates existing question
    public Question updateQuestion(Long questionId, Question question) {
        question.setId(questionId);
        return questionRepository.save(question);
    }

    // Deletes question by ID
    public void deleteQuestionById(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    // Returns questions by form ID
    public List<Question> findAllQuestionsByFormId(Long formId) {
        return questionRepository.findAllByFormId(formId);
    }

    // Returns questions by required status
    public List<Question> findQuestionByRequired(Boolean isRequired) {
        return questionRepository.findByRequired(isRequired);
    }

    public List<Question> findQuestionByQuestionType(Question.QuestionType questionType) {
        return questionRepository.findByQuestionType(questionType);
    }
}
