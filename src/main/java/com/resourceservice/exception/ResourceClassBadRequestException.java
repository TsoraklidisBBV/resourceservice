package com.resourceservice.exception;

public class ResourceClassBadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceClassBadRequestException(String msg) {
        super(msg);
    }
}