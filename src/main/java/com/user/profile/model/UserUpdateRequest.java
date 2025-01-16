package com.user.profile.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotBlank
    private String username;
    @Size(min=3,message = "firstName  must be at least 3 characters long")
    private String firstName;
    private String lastName;
    @Email
    private String email;
}
