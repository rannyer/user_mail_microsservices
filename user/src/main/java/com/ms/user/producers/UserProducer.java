package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.User;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user){
        var emailDto = new EmailDto();
        emailDto.setUserId(user.getId());
        emailDto.setEmailTo(user.getEmail());
        emailDto.setSubject("Usuario cadastrado com sucesso!");
        emailDto.setText(user.getNome()+", seja bem vindo(a) ao sistema 😎😎😎");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
