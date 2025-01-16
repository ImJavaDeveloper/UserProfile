package com.user.profile.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String message)
    {
        super(message);
    }
}
