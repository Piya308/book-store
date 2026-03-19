package com.priyanka.order_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priyanka.order_service.domain.ApplicationProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMQConfig {

    private final ApplicationProperties properties;

    RabbitMQConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    DirectExchange exchange(AmqpAdmin admin) {
        DirectExchange ex = new DirectExchange(properties.orderEventsExchange());
        admin.declareExchange(ex);  // forces creation
        return ex;
    }

    @Bean
    Queue newOrdersQueue(AmqpAdmin admin) {
        Queue q = QueueBuilder.durable(properties.newOrdersQueue()).build();
        admin.declareQueue(q);  // forces creation
        return q;
    }

    @Bean
    Binding newOrdersQueueBinding(AmqpAdmin admin, Queue newOrdersQueue, DirectExchange exchange) {
        Binding b = BindingBuilder.bind(newOrdersQueue).to(exchange).with(properties.newOrdersQueue());
        admin.declareBinding(b);  // forces creation
        return b;
    }

    @Bean
    Queue deliveredOrdersQueue(AmqpAdmin admin) {
        Queue q = QueueBuilder.durable(properties.deliveredOrdersQueue()).build();
        admin.declareQueue(q);
        return q;
    }

    @Bean
    Binding deliveredOrdersQueueBinding(AmqpAdmin admin, Queue deliveredOrdersQueue, DirectExchange exchange) {
        Binding b = BindingBuilder.bind(deliveredOrdersQueue).to(exchange).with(properties.deliveredOrdersQueue());
        admin.declareBinding(b);
        return b;
    }

    @Bean
    Queue cancelledOrdersQueue(AmqpAdmin admin) {
        Queue q = QueueBuilder.durable(properties.cancelledOrdersQueue()).build();
        admin.declareQueue(q);
        return q;
    }

    @Bean
    Binding cancelledOrdersQueueBinding(AmqpAdmin admin, Queue cancelledOrdersQueue, DirectExchange exchange) {
        Binding b = BindingBuilder.bind(cancelledOrdersQueue).to(exchange).with(properties.cancelledOrdersQueue());
        admin.declareBinding(b);
        return b;
    }

    @Bean
    Queue errorOrdersQueue(AmqpAdmin admin) {
        Queue q = QueueBuilder.durable(properties.errorOrdersQueue()).build();
        admin.declareQueue(q);
        return q;
    }

    @Bean
    Binding errorOrdersQueueBinding(AmqpAdmin admin, Queue errorOrdersQueue, DirectExchange exchange) {
        Binding b = BindingBuilder.bind(errorOrdersQueue).to(exchange).with(properties.errorOrdersQueue());
        admin.declareBinding(b);
        return b;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }
}