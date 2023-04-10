package com.evertri.ecrf.authentication;

import com.evertri.ecrf.model.Device;
import com.evertri.ecrf.model.Study;
import com.evertri.ecrf.model.User;
import com.evertri.ecrf.repository.DeviceRepository;
import com.evertri.ecrf.repository.StudyRepository;
import com.evertri.ecrf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final DeviceRepository deviceRepository;
    private final TokenUtil tokenUtil;


    @Autowired
    public AuthenticationService(UserRepository userRepository, StudyRepository studyRepository, DeviceRepository deviceRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.studyRepository = studyRepository;
        this.deviceRepository = deviceRepository;
        this.tokenUtil = tokenUtil;
    }

    public boolean isAuthorized(String username, String macAddress, Long studyId) {
        User user = userRepository.findByUsername(username);
        if (user == null || !isUserAuthorized(username, studyId)) {
            return false;
        }

        Study study = studyRepository.findById(studyId).orElse(null);
        if (study == null) {
            return false;
        }

        Device device = deviceRepository.findByMacAddress(macAddress);
        if (device == null || !isDeviceAuthorized(macAddress)) {
            return false;
        }
        return user.hasAuthorization(studyId);
    }


    protected boolean isUserAuthorized(String username, Long studyId) {
        User user = userRepository.findByUsername(username);
        Study study = studyRepository.findById(studyId).orElse(null);
        return ((user != null) && (study != null) && (user.hasAuthorization(studyId)));
    }

    private boolean isDeviceAuthorized(String macAddress) {
        Device device = deviceRepository.findByMacAddress(macAddress);
        return ((device != null) && (device.isAuthorized()));
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}

