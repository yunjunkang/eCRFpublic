package com.evertri.ecrf.repository;

import com.evertri.ecrf.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    // Finds a device with the given MAC address
    Device findByMacAddress(String macAddress);
}
