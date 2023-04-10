package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Form;
import com.evertri.ecrf.service.FormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for Form model
 */
@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    /**
     * Method to create a new form
     * @param form form object to be saved
     * @return saved form object
     */
    @PostMapping
    public ResponseEntity<Form> create(@RequestBody Form form) {
        Form savedForm = formService.create(form);
        return new ResponseEntity<>(savedForm, HttpStatus.CREATED);
    }

    /**
     * Method to find all forms
     * @return list of all forms
     */
    @GetMapping
    public ResponseEntity<List<Form>> getAllForms() {
        List<Form> forms = formService.getAllForms();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    /**
     * Method to find a form by its id
     * @param formId form id
     * @return form object
     */

    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable(value = "id") Long formId) {
        Form form = formService.getFormById(formId).orElse(null);
        return new ResponseEntity<>(form, HttpStatus.OK);
    }
    /**
     * Method to update a form
     * @param id form id
     * @param form form object with updated values
     * @return updated form object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable long id, @RequestBody Form form) {
        Form updatedForm = formService.updateForm(id, form);
        return new ResponseEntity<>(updatedForm, HttpStatus.OK);
    }

    /**
     * Method to delete a form by its id
     * @param id form id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormById(@PathVariable long id) {
        formService.deleteFormById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
