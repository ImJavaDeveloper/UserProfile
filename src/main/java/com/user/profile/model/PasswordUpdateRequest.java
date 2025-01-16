package com.user.profile.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordUpdateRequest {

    @Size(min = 6,message = "username must be at least 6 characters long")
    private String username;

    @Size(min=6,message = "password must be at least 6 characters long")
    @NotBlank
    private String password;

    @NotBlank
    @Size(min=6,message = "password must be at least 6 characters long")
    private String oldPassword;
}
