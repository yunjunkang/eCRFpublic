package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Randomization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RandomizationRepository extends JpaRepository<Randomization, Long> {
    Randomization findByTrialId(Long trialId);


    // Custom method to find randomization by control
    List<Randomization> findByControl(String control);

    // Custom method to find randomization by test group
    List<Randomization> findByTestGroup(String testGroup);

}
