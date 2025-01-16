package com.user.profile.kafka.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    /*@Bean
    public ProducerFactory<String,CredentialEvent> kafkaProducer()
    {
        Map<String,Object> props=new HashMap<>();
        props.put("bootstrap.server","kafka:29092");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,)
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean

    public KafkaTemplate<String,CredentialEvent> kafkaTemplater()
    {
        return new KafkaTemplate<>(kafkaProducer());
    }*/
}
