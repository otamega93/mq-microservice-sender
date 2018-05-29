package com.example.mqmicroservicesender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mqmicroservicesender.mq.MessageConsumer;

@Configuration
@EnableRabbit
public class RabbitMqConfiguration {

    public final static String queueName = "com.baeldung.spring-amqp-simple.queue";
    public final static String queueNamealter = "com.baeldung.spring-amqp-simple.queue.alter";
    public final static String exchangeName = "com.baeldung.spring-amqp-simple.exchange";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    Queue queueAlter() {
        return new Queue(queueNamealter, false);
    }
    
    @Bean
    Exchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }
    
    @Bean
    Binding bindingAlter(Queue queueAlter, DirectExchange exchange) {
        return BindingBuilder.bind(queueAlter).to(exchange).with(queueNamealter);
    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//            MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueNamealter);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

    @Bean
    MessageListenerAdapter listenerAdapter(MessageConsumer messageReceiver) {
        return new MessageListenerAdapter(messageReceiver, "receiveMessage");
    }
}
