package com.goosfraba.city_parking.exceptions;

public class CityAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CityAlreadyExistsException(String msg) {
        super(msg);
    }
}
