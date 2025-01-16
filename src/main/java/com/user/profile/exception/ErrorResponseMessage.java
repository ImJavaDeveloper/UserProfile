package com.user.profile.exception;

import lombok.Data;

@Data
public class ErrorResponseMessage {


    private int status;
    private String message;
}
