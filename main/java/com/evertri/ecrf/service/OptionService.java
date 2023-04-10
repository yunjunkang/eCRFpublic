package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Option;
import com.evertri.ecrf.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public Option createOption(Option option) {
        return optionRepository.save(option);
    }

    public Optional<Option> getOptionById(Long id) {
        return optionRepository.findById(id);
    }

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Option updateOption(Option option) {
        return optionRepository.save(option);
    }

    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }
}
