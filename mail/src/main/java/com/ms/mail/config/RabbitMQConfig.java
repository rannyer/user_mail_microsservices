package com.ms.mail.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.email.name}")
    private String emailQueue;

//    @Value("${broker.queue.email.response}")
//    private String emailResponseQueue;


    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue, true);
    }

//    @Bean
//    public Queue emailResponseQueue(){
//        return new Queue(emailResponseQueue, true);
//    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
