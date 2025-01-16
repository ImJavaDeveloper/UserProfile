package com.user.profile.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    @NotBlank(message = "username can not be empty or null")
    @Size(min = 6,message = "username must be at least 6 characters long")
    private String username;
    @Size(min=3,message = "firstName  must be at least 3 characters long")
    private String firstName;
    private String lastName;
    @Email
    private String email;

}
