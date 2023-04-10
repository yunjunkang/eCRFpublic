CREATE DATABASE IF NOT EXISTS eCRF_db;
USE eCRF_db;

CREATE TABLE IF NOT EXISTS hospital
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS study
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255),
    status        VARCHAR(255),
    creation_date TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS form
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    name          VARCHAR(255) NOT NULL,
    description   TEXT,
    creation_date DATE         NOT NULL,
    study_id      BIGINT       NOT NULL,
    status        VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (study_id) REFERENCES study (id)
);


CREATE TABLE IF NOT EXISTS question
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255) NOT NULL,
    question_type VARCHAR(255) NOT NULL,
    required      BIT          NOT NULL,
    form_id       BIGINT       NOT NULL,
    FOREIGN KEY (form_id) REFERENCES form (id)
);

CREATE TABLE IF NOT EXISTS option
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    value       VARCHAR(255) NOT NULL,
    other       BOOLEAN      NOT NULL DEFAULT FALSE,
    question_id BIGINT       NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id)
);


CREATE TABLE IF NOT EXISTS patient
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255),
    date_of_birth   DATE,
    national_id     VARCHAR(255),
    gender          VARCHAR(255),
    medical_history TEXT,
    study_id        BIGINT,
    FOREIGN KEY (study_id) REFERENCES study (id)
);

CREATE TABLE IF NOT EXISTS consent
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_signed    TIMESTAMP    NOT NULL,
    signature_path VARCHAR(255) NOT NULL,
    patient_id     BIGINT       NOT NULL,
    study_id       BIGINT       NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient (id),
    FOREIGN KEY (study_id) REFERENCES study (id)
);

CREATE TABLE IF NOT EXISTS user
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_study
(
    user_id  BIGINT NOT NULL,
    study_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, study_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (study_id) REFERENCES study (id)
);

CREATE TABLE IF NOT EXISTS user_hospital
(
    user_id     BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, hospital_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (hospital_id) REFERENCES hospital (id)
);

CREATE TABLE IF NOT EXISTS role
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);


CREATE TABLE IF NOT EXISTS study_hospital
(
    study_id    BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    PRIMARY KEY (study_id, hospital_id),
    FOREIGN KEY (study_id) REFERENCES study (id),
    FOREIGN KEY (hospital_id) REFERENCES hospital (id)
);

CREATE TABLE IF NOT EXISTS audit_trail
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    entity_name VARCHAR(255) NOT NULL,
    entity_id   BIGINT       NOT NULL,
    action      VARCHAR(255) NOT NULL,
    timestamp   TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS visit
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    visit_date DATE         NOT NULL,
    status     VARCHAR(255) NOT NULL,
    patient_id BIGINT       NOT NULL,
    form_id    BIGINT       NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient (id),
    FOREIGN KEY (form_id) REFERENCES form (id)
);

CREATE TABLE IF NOT EXISTS answer
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    value       VARCHAR(255) NOT NULL,
    question_id BIGINT       NOT NULL,
    form_id     BIGINT       NOT NULL,
    patient_id  BIGINT       NOT NULL,
    visit_id    BIGINT,
    FOREIGN KEY (question_id) REFERENCES question (id),
    FOREIGN KEY (form_id) REFERENCES form (id),
    FOREIGN KEY (patient_id) REFERENCES patient (id),
    FOREIGN KEY (visit_id) REFERENCES visit (id)
);

CREATE TABLE IF NOT EXISTS form_question
(
    form_id     BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    PRIMARY KEY (form_id, question_id),
    FOREIGN KEY (form_id) REFERENCES form (id),
    FOREIGN KEY (question_id) REFERENCES question (id)
);






