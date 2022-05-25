package com.resourceservice.exception;

class ResourceClassNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceClassNotFoundException(String msg) {
        super(msg);
    }
}