package com.evertri.ecrf.controller;

import com.evertri.ecrf.exception.ResourceNotFoundException;
import com.evertri.ecrf.model.Question;
import com.evertri.ecrf.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Get all questions
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    // Create a new question
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    // Get a single question by id
    @GetMapping("/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable(value = "id") Long questionId) {
        Question question = questionService.findQuestionById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + questionId));
        return ResponseEntity.ok().body(question);
    }

    // Update a question
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable(value = "id") Long questionId, @RequestBody Question questionDetails) {
        return questionService.updateQuestion(questionId, questionDetails);
    }

    // Delete a question
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable(value = "id") Long questionId) {
        questionService.deleteQuestionById(questionId);
    }

    // Get questions by required status
    @GetMapping("/required/{isRequired}")
    public List<Question> findQuestionByRequired(@PathVariable(value = "isRequired") boolean isRequired) {
        return questionService.findQuestionByRequired(isRequired);
    }

    // Get questions by question type
    @GetMapping("/type/{questionType}")
    public List<Question> findQuestionByQuestionType(@PathVariable(value = "questionType") Question.QuestionType questionType) {
        return questionService.findQuestionByQuestionType(questionType);
    }
}
