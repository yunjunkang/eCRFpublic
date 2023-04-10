package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Answer;
import com.evertri.ecrf.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Answer model
 */
@RestController
@RequestMapping("/answers")
public class AnswerController {

    /**
     * Autowired answer service
     */
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * Method to retrieve all answers
     * @return list of answers
     */
    @GetMapping
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Answer answer = answerService.getAnswerById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> saveAnswer(@RequestBody Answer answer) {
        answerService.saveAnswer(answer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        Answer updatedAnswer = answerService.updateAnswer(id, answer);
        return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable Long id) {
        answerService.deleteAnswerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /**
     * Method to find all answers for a given form
     * @param formId form id
     * @return list of answers
     */
    @GetMapping("/form/{formId}")
    public List<Answer> getAllAnswersByFormId(@PathVariable Long formId) {
        return answerService.getAllAnswersByFormId(formId);
    }

    /**
     * Method to find all answers for a given question
     * @param questionId question id
     * @return list of answers
     */
    @GetMapping("/question/{questionId}")
    public List<Answer> getAllAnswersByQuestionId(@PathVariable Long questionId) {
        return answerService.getAllAnswersByQuestionId(questionId);
    }

    @GetMapping("/form/{formId}/patient/{patientId}")
    public List<Answer> getAllAnswersByFormIdAndPatientId(@PathVariable Long formId, @PathVariable Long patientId) {
        return answerService.getAllAnswersByFormIdAndPatientId(formId, patientId);
    }

}
