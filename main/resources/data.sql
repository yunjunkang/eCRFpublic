USE eCRF_db;

INSERT INTO hospital (name) VALUES ('St. Mary''s Hospital'), ('City Hospital'), ('General Hospital');

INSERT INTO study (name, description, status, creation_date)
VALUES ('COVID-19 Treatment Study', 'A study to test new treatments for COVID-19', 'active', '2022-03-01'),
       ('Cancer Prevention Study', 'A study to investigate the effectiveness of a new cancer prevention drug', 'planned', '2023-01-01'),
       ('Heart Disease Study', 'A study to assess the impact of lifestyle changes on heart disease risk', 'completed', '2021-06-01');

INSERT INTO form (name, description, creation_date, study_id, status)
VALUES ('Patient Demographics', 'Collects information about the patient', '2022-03-01', 1, 'active'),
       ('COVID-19 Symptom Questionnaire', 'Collects information about the patient''s symptoms related to COVID-19', '2022-03-01', 1, 'active'),
       ('Drug Effectiveness Assessment', 'Assesses the effectiveness of the new drug on cancer prevention', '2023-01-01', 2, 'planned');

INSERT INTO question (question_text, question_type, required, form_id)
VALUES ('What is your name?', 'text', 1, 1),
       ('What is your date of birth?', 'date', 1, 1),
       ('Have you experienced any of the following COVID-19 symptoms?', 'checkbox', 1, 2),
       ('Did the drug have any side effects?', 'radio', 0, 3);

INSERT INTO patient (name, date_of_birth, national_id, gender, medical_history, study_id)
VALUES ('John Smith', '1980-01-01', '123456789', 'male', 'none', 1),
       ('Jane Doe', '1995-05-15', '987654321', 'female', 'previous cancer history', 2),
       ('Bob Johnson', '1972-12-31', '246810121', 'male', 'high blood pressure', 3);

INSERT INTO consent (date_signed, signature_path, patient_id, study_id)
VALUES ('2022-03-01 10:00:00', 'John_Smith_Signature.png', 1, 1),
       ('2023-01-01 10:00:00', 'Jane_Doe_Signature.png', 2, 2);

INSERT INTO user (username, password) VALUES ('admin', 'password');

INSERT INTO user_study (user_id, study_id) VALUES (1, 1), (1, 2);

INSERT INTO user_hospital (user_id, hospital_id) VALUES (1, 1), (1, 2), (1, 3);

INSERT INTO role (name) VALUES ('Admin'), ('User');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1), (1, 2);

INSERT INTO study_hospital (study_id, hospital_id) VALUES (1, 1), (2, 2), (3, 3);

INSERT INTO audit_trail (entity_name, entity_id, action, timestamp)
VALUES ('patient', 1, 'insert', NOW()),
       ('patient', 2, 'insert', NOW()),
       ('form', 1, 'insert',NOW()),
       ('form', 2, 'insert', NOW()),
       ('question', 1, 'insert', NOW()),
       ('question', 2, 'insert', NOW()),
       ('patient', 3, 'insert', NOW());

INSERT INTO answer (value, question_id, form_id, patient_id)
VALUES ('John Smith', 1, 1, 1),
       ('1980-01-01', 2, 1, 1),
       ('fever', 3, 2, 1),
       ('no', 4, 3, 2);

INSERT INTO visit (visit_date, status, patient_id, form_id)
VALUES ('2022-03-15', 'completed', 1, 1),
       ('2022-03-22', 'completed', 1, 2),
       ('2023-01-15', 'completed', 2, 1),
       ('2023-01-22', 'completed', 2, 3);
