package com.evertri.ecrf;

import com.evertri.ecrf.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseInitializer {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AuditTrailRepository auditTrailRepository;
    @Autowired
    private ConsentRepository consentRepository;
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void resetDatabase() {
        // Delete all the data
        answerRepository.deleteAll();
        auditTrailRepository.deleteAll();
        consentRepository.deleteAll();
        dashboardRepository.deleteAll();
        deviceRepository.deleteAll();
        formRepository.deleteAll();
        hospitalRepository.deleteAll();
        patientRepository.deleteAll();
        questionRepository.deleteAll();
        roleRepository.deleteAll();
        studyRepository.deleteAll();
        userRepository.deleteAll();

        // Your application should automatically recreate the tables based on your entity classes
    }
}
