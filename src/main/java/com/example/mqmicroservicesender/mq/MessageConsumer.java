package com.example.mqmicroservicesender.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.mqmicroservicesender.config.RabbitMqConfiguration;

@Component
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitListener(queues = {RabbitMqConfiguration.queueName})
    public void receiveMessage(String message) {
        logger.info("Received Message: " + message);
    }
}
