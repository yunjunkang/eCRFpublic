package com.evertri.ecrf.model;

import jakarta.persistence.*;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "authorized")
    private boolean authorized;

    // default constructor
    public Device() {}

    // constructor with MAC address and authorization
    public Device(String macAddress, boolean authorized) {
        this.macAddress = macAddress;
        this.authorized = authorized;
    }

    // getter and setter for id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // getter and setter for MAC address
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    // getter and setter for authorization
    public boolean isAuthorized() {
        return authorized;
    }
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
