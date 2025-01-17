package com.user.profile.kafka.service;


import com.user.profile.kafka.event.UserPasswordChangeEvent;
import com.user.profile.kafka.event.UserRegistrationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageProducer implements MessageProducer {

    @Value("${spring.kafka.topic}")
    private String topic;

    @Autowired
    KafkaTemplate<String, UserRegistrationEvent> userRegistrationKafkaTemplate;

    @Autowired
    KafkaTemplate<String, UserPasswordChangeEvent> userPasswordChangeKafkaTemplate;

    @Override
    public void sendUserRegistrationEvent(UserRegistrationEvent userRegistrationEvent) {
        log.info("Sending user registration event on topic :{}",topic);
        userRegistrationKafkaTemplate.send(topic,userRegistrationEvent);
    }

    @Override
    public void sendUserPasswordChangeEvent(UserPasswordChangeEvent userPasswordChangeEvent) {
        log.info("Sending user password change event on topic :{}",topic);
        userPasswordChangeKafkaTemplate.send(topic,userPasswordChangeEvent);
    }
}
