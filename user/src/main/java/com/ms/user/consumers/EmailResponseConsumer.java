package com.ms.user.consumers;

import com.ms.user.models.EmailStatusResponse;
import com.ms.user.services.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailResponseConsumer {

    private final UserService userService;

    public EmailResponseConsumer(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "${broker.queue.email.response}")
    public void listenEmailResponse(EmailStatusResponse response){
        System.out.println("FUNCIONOU");
        userService.updateStatus(response);
    }
}
