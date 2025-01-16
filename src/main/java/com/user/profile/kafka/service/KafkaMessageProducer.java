package com.user.profile.kafka.service;


import com.user.profile.kafka.event.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducer implements MessageProducer {

    @Value("${spring.kafka.topic}")
    private String topic;

    @Autowired
    KafkaTemplate<String, UserRegistrationEvent> userRegistrationKafkaTemplate;

    @Override
    public void sendUserRegistrationEvent(UserRegistrationEvent userRegistrationEvent) {
        userRegistrationKafkaTemplate.send(topic,userRegistrationEvent);
    }
}
