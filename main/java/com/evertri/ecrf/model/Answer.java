package com.evertri.ecrf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type")
public abstract class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "form_id")
    private Form form;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    public Answer() {
    }

    public Answer(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}
@Entity
@DiscriminatorValue("INTEGER")
class IntegerAnswer extends Answer {
    @Column(name = "value")
    private Integer value;

    public IntegerAnswer() {
        super();
    }

    public IntegerAnswer(Question question, Integer value) {
        super(question);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

@Entity
@DiscriminatorValue("STRING_ANSWER")
class StringAnswer extends Answer {
    @Column(name = "value")
    private String value;

    public StringAnswer() {
        super();
    }

    public StringAnswer(Question question, String value) {
        super(question);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

@Entity
@DiscriminatorValue("DATE")
class DateAnswer extends Answer {
    @Column(name = "value")
    private LocalDate value;

    public DateAnswer() {
        super();
    }

    public DateAnswer(Question question, LocalDate value) {
        super(question);
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public void setValue(LocalDate value) {
        this.value = value;
    }
}

@Entity
@DiscriminatorValue("NUMERICAL_ANSWER")
class NumericalAnswer extends Answer {
    @Column(name = "value")
    private Double value;

    public NumericalAnswer() {
        super();
    }

    public NumericalAnswer(Question question, Double value) {
        super(question);
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}


@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
class MultipleChoiceAnswer extends Answer {
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Option> options;

    @Column(name = "other_value")
    private String otherValue;

    public MultipleChoiceAnswer() {
        super();
    }

    public MultipleChoiceAnswer(Question question, List<Option> options, String otherValue) {
        super(question);
        this.options = options;
        this.otherValue = otherValue;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }
}

@Entity
@DiscriminatorValue("PICK_ALL_THAT_APPLY")
class PickAllThatApplyAnswer extends Answer {
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Option> options;

    @Column(name = "other_value")
    private String otherValue;

    public PickAllThatApplyAnswer() {
        super();
    }

    public PickAllThatApplyAnswer(Question question, List<Option> options, String otherValue) {
        super(question);
        this.options = options;
        this.otherValue = otherValue;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }
}



