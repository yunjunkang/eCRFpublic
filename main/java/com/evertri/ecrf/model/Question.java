package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private boolean required;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;

    public Question() {
    }

    public Question(String questionText, boolean required) {
        this.questionText = questionText;
        this.required = required;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}


@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
class MultipleChoiceQuestion extends Question {
    // Attributes and methods specific to multiple-choice questions
}

@Entity
@DiscriminatorValue("YES_NO")
class YesNoQuestion extends Question {
    // Attributes and methods specific to yes-no questions
}

@Entity
@DiscriminatorValue("")
class IntegerQuestion extends Question {

}

@Entity
@DiscriminatorValue("")
class StringQuestion extends Question {

}

@Entity
@DiscriminatorValue("")
class NumericalQuestion extends Question {

}

@Entity
@DiscriminatorValue("")
class PickAllThatApplyQuestion extends Question {

}