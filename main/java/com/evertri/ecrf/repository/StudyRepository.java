package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    Study findByName(String name);
    List<Study> findByNameContaining(String name);
    List<Study> findByDescriptionContaining(String description);


    // Custom methods


    List<Study> findByStatus(String status);
    List<Study> findByCreationDateBetween(LocalDateTime start, LocalDateTime end);

    List<Study> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}
