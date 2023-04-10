package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Device;
import com.evertri.ecrf.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Handles HTTP POST requests to /devices, which creates a new device
    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestParam("macAddress") String macAddress) {
        Device device = deviceService.createDevice(macAddress);
        return new ResponseEntity<>(device, HttpStatus.CREATED);
    }

    // Handles HTTP DELETE requests to /devices/{id}, which deletes the device with the given ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Long id) {
        deviceService.deleteDevice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
