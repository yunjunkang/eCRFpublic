package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    // Custom method to find a dashboard by its form name
    Dashboard findByForm_Name(String name);

    // Custom method to find all dashboards containing a specific word in the form name
    List<Dashboard> findByForm_NameContaining(String word);
}
