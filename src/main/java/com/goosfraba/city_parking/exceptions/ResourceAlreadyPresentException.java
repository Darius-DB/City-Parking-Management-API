package com.goosfraba.city_parking.exceptions;

public class ResourceAlreadyPresentException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceAlreadyPresentException(String msg) {
        super(msg);
    }
}
