package com.user.profile.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPasswordChangeEvent {

    private String username;
    private String password;
    private final String event="USER_PASSWORD_CHANGE";
}
