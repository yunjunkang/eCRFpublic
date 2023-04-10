package com.evertri.ecrf.exception;

public class DeviceNotAuthorizedException extends RuntimeException {
    public DeviceNotAuthorizedException(String message) {
        super(message);
    }
}
