package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Option;
import com.evertri.ecrf.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping
    public ResponseEntity<Option> createOption(@RequestBody Option option) {
        return new ResponseEntity<>(optionService.createOption(option), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id) {
        Optional<Option> option = optionService.getOptionById(id);
        if (option.isPresent()) {
            return new ResponseEntity<>(option.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Option>> getAllOptions() {
        return new ResponseEntity<>(optionService.getAllOptions(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Option> updateOption(@PathVariable Long id, @RequestBody Option updatedOption) {
        Optional<Option> option = optionService.getOptionById(id);
        if (option.isPresent()) {
            Option currentOption = option.get();
            currentOption.setValue(updatedOption.getValue());
            currentOption.setQuestion(updatedOption.getQuestion());
            return new ResponseEntity<>(optionService.updateOption(currentOption), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
