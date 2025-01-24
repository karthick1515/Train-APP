package com.securityservice.exception;

public class ResouceAlreadyExistsException extends Exception{
    public ResouceAlreadyExistsException() {
    }

    public ResouceAlreadyExistsException(String message) {
        super(message);
    }
}
