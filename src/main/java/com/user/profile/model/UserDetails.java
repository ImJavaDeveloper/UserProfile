package com.user.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetails {

    private String firstName;
    private String lastName;
    private String email;
}
