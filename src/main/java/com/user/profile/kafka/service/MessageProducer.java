package com.user.profile.kafka.service;

import com.user.profile.kafka.event.UserPasswordChangeEvent;
import com.user.profile.kafka.event.UserRegistrationEvent;

public interface MessageProducer {

    void sendUserRegistrationEvent(UserRegistrationEvent userRegistrationEvent);
    void sendUserPasswordChangeEvent(UserPasswordChangeEvent userPasswordChangeEvent);

}
