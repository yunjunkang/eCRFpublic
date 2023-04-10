package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Device;
import com.evertri.ecrf.model.User;
import com.evertri.ecrf.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = registrationService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/device")
    public ResponseEntity<Device> registerDevice(@RequestBody Device device) {
        Device registeredDevice = registrationService.registerDevice(device);
        return new ResponseEntity<>(registeredDevice, HttpStatus.CREATED);
    }
}
