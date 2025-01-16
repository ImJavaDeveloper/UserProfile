package com.user.profile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="userCred")
@Data
public class UserCredential {

    @Id
    @NotBlank(message = "username can not be empty or null")
    @Size(min = 6,message = "username must be at least 6 characters long")
    private String username;
    @Size(min=6,message = "password must be at least 6 characters long")
    private String password;
}
