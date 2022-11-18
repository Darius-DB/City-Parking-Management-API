package com.goosfraba.city_parking.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
