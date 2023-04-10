package com.evertri.ecrf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertri.ecrf.model.Dashboard;
import com.evertri.ecrf.repository.DashboardRepository;

import java.util.List;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    @Autowired
    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    // get all dashboards
    public List<Dashboard> getAllDashboards() {
        return dashboardRepository.findAll();
    }

    // get dashboard by id
    public Dashboard getDashboardById(Long id) {
        return dashboardRepository.findById(id).orElse(null);
    }

    // add a new dashboard
    public Dashboard addDashboard(Dashboard dashboard) {
        return dashboardRepository.save(dashboard);
    }

    // update existing dashboard
    public Dashboard updateDashboard(Long id, Dashboard dashboard) {
        return dashboardRepository.save(dashboard);
    }

    // delete dashboard by id
    public void deleteDashboard(Long id) {
        dashboardRepository.deleteById(id);
    }

    // get dashboard by form name
    public Dashboard getDashboardByFormName(String name) {
        return dashboardRepository.findByForm_Name(name);
    }

    // get all dashboards containing a specific word in the form name
    public List<Dashboard> getDashboardsByFormNameContaining(String word) {
        return dashboardRepository.findByForm_NameContaining(word);
    }
}
