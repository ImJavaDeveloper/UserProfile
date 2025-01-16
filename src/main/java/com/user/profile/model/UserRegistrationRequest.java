package com.user.profile.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "username can not be empty or null")
    @Size(min = 6,message = "username must be at least 6 characters long")
    private String username;
    @Size(min=6,message = "password must be at least 6 characters long")
    private String password;
    @Size(min=3,message = "firstName  must be at least 3 characters long")
    private String firstName;
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
}
