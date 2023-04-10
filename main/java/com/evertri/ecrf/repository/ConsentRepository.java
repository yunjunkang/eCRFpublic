package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Consent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {

}
