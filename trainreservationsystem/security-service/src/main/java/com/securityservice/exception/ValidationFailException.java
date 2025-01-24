package com.securityservice.exception;

public class ValidationFailException extends  Exception{
    public ValidationFailException() {
    }
    public ValidationFailException(String message) {
        super(message);
    }
}
