package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Device;
import com.evertri.ecrf.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // Creates a new device with the given MAC address
    public Device createDevice(String macAddress) {
        Device device = new Device(macAddress, true);
        return deviceRepository.save(device);
    }

    // Deletes the device with the given ID
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    // Checks if the given MAC address is authorized
    public boolean isDeviceAuthorized(String macAddress) {
        Device device = deviceRepository.findByMacAddress(macAddress);
        return device != null;
    }
}
