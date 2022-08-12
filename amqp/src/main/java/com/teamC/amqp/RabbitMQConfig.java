package com.teamC.amqp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMQConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public MessageConverter jacksonConverter() {
        MessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }

    // ENABLES US TO PUBLISH MESSAGES TO AN EXCHANGE
    @Bean
    public AmqpTemplate amqpTemplate () {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }

    // ENABLES RECEIVING MESSAGE FROM A QUEUE VIA JACKSONCONVERTER
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }



    public static final String notificationQueue = "internal.exchange";
    public static final String internalExchange = "notification.queue";
    public static final String internalNotificationRoutingKey = "internal.notification.routing-key";


    // the below gives us access to the values in app.yml file, and we're saving them into the below String variables
//    @Value("${rabbitmq.exchanges.internal}")
//    private String internalExchange; // value for this is coming from the app.yml file using the above annotation
//
//    @Value("${rabbitmq.queue.notification}")
//    private String notificationQueue;
//
//    @Value("${rabbitmq.routing-keys.internal-notification}")
//    private String internalNotificationRoutingKey;

    // DEFINING THE EXCHANGE
    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    // DEFINING THE QUEUE
    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueue);
    }

    // DEFINING THE BINDING BETWEEN QUEUE AND EXCHANGE WITH THE ROUTING KEY FIELD
    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationQueue() {
        return notificationQueue;
    }

    public String getInternalNotificationRoutingKey() {
        return internalNotificationRoutingKey;
    }
}
