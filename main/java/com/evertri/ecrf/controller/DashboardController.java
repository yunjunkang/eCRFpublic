package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Dashboard;
import com.evertri.ecrf.model.Patient;
import com.evertri.ecrf.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Get a list of all dashboards
    @GetMapping("/")
    public ResponseEntity<List<Dashboard>> getAllDashboards() {
        List<Dashboard> dashboards = dashboardService.getAllDashboards();
        return new ResponseEntity<>(dashboards, HttpStatus.OK);
    }

    // Get a specific dashboard by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Dashboard> getDashboardById(@PathVariable("id") Long id) {
        Dashboard dashboard = dashboardService.getDashboardById(id);
        return dashboard != null ? ResponseEntity.ok(dashboard) : ResponseEntity.notFound().build();
    }

    // Create a new dashboard
    @PostMapping("/")
    public ResponseEntity<Dashboard> saveDashboard(@RequestBody Dashboard dashboard) {
        Dashboard createdDashboard = dashboardService.addDashboard(dashboard);
        return new ResponseEntity<>(createdDashboard, HttpStatus.CREATED);
    }

    // Update an existing dashboard by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Dashboard> updateDashboard(@PathVariable("id") Long id, @RequestBody Dashboard dashboard) {
        Dashboard updatedDashboard = dashboardService.updateDashboard(id, dashboard);
        return updatedDashboard != null ? ResponseEntity.ok(updatedDashboard) : ResponseEntity.notFound().build();
    }

    // Delete a dashboard by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDashboardById(@PathVariable("id") Long id) {
        dashboardService.deleteDashboard(id);
        return ResponseEntity.noContent().build();
    }


    // method to get dashboard by form name
    @GetMapping("/byFormName/{name}")
    public ResponseEntity<Dashboard> getDashboardByFormName(@PathVariable String name) {
        Dashboard dashboard = dashboardService.getDashboardByFormName(name);
        return new ResponseEntity<>(dashboard, HttpStatus.OK);
    }


    @GetMapping("/byFormNameContaining/{word}")
    public ResponseEntity<List<Dashboard>> getDashboardsByFormNameContaining(@PathVariable String word) {
        List<Dashboard> dashboards = dashboardService.getDashboardsByFormNameContaining(word);
        return new ResponseEntity<>(dashboards, HttpStatus.OK);
    }

}
