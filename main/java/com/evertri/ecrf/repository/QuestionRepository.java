package com.evertri.ecrf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.evertri.ecrf.model.*;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findByQuestionText(String questionText);

    List<Question> findAllByFormId(Long formId);

    List<Question> findByQuestionType(Question.QuestionType questionType);

    List<Question> findByRequired(Boolean isRequired);
}
