package com.resourceservice.exception;

public class ResourceClassNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceClassNotFoundException(String msg) {
        super(msg);
    }
}