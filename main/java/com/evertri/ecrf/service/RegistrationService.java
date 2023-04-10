package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Device;
import com.evertri.ecrf.model.User;
import com.evertri.ecrf.repository.DeviceRepository;
import com.evertri.ecrf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(User user) {
        // Encode the password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Device registerDevice(Device device) {
        device.setAuthorized(true);
        return deviceRepository.save(device);
    }
}
