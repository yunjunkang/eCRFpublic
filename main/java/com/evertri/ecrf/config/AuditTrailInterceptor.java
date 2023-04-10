package com.evertri.ecrf.config;

import com.evertri.ecrf.model.*;
import com.evertri.ecrf.repository.AuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@Component
public class AuditTrailInterceptor {

    private final AuditTrailRepository auditTrailRepository;

    @Autowired
    public AuditTrailInterceptor(AuditTrailRepository auditTrailRepository) {
        this.auditTrailRepository = auditTrailRepository;
    }

    @PostPersist
    public void onPostPersist(Object entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        handleEvent(entity, "INSERT");
    }

    @PostUpdate
    public void onPostUpdate(Object entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        handleEvent(entity, "UPDATE");
    }

    @PostRemove
    public void onPostRemove(Object entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        handleEvent(entity, "DELETE");
    }

    private enum EntityMapping {
        ANSWER(Answer.class, "Answer"),
        AUDIT_TRAIL(AuditTrail.class, "AuditTrail"),
        CONSENT(Consent.class, "Consent"),
        DASHBOARD(Dashboard.class, "Dashboard"),
        DEVICE(Device.class, "Device"),
        FORM(Form.class, "Form"),
        HOSPITAL(Hospital.class, "Hospital"),
        PATIENT(Patient.class, "Patient"),
        QUESTION(Question.class, "Question"),
        ROLE(Role.class, "Role"),
        STUDY(Study.class, "Study"),
        USER(User.class, "User");

        private final Class<?> entityClass;
        private final String entityName;

        EntityMapping(Class<?> entityClass, String entityName) {
            this.entityClass = entityClass;
            this.entityName = entityName;
        }

        public Class<?> getEntityClass() {
            return entityClass;
        }

        public String getEntityName() {
            return entityName;
        }
    }

    private void handleEvent(Object entity, String action) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (EntityMapping entityMapping : EntityMapping.values()) {
            if (entityMapping.getEntityClass().isInstance(entity)) {
                AuditTrail auditTrail = new AuditTrail();
                auditTrail.setEntityName(entityMapping.getEntityName());
                auditTrail.setEntityId((Long) entity.getClass().getMethod("getId").invoke(entity));
                auditTrail.setAction(action);
                auditTrail.setTimestamp(new Date());
                auditTrailRepository.save(auditTrail);
                break;
            }
        }
    }

}
