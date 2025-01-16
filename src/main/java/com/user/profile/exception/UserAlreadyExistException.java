package com.user.profile.exception;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String m)
    {
        super(m);
    }
}
