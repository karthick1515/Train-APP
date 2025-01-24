package com.securityservice.exception;

public class UserAlreadyRegisteredException  extends  Exception{
    public UserAlreadyRegisteredException() {
        super();
    }

    public UserAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
