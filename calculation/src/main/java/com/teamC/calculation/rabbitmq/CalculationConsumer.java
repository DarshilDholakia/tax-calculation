package com.teamC.calculation.rabbitmq;

import com.teamC.amqp.RabbitMQConfig;
import com.teamC.calculation.CalculationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j // enables users to make logs to the console upon running the application
@AllArgsConstructor
public class CalculationConsumer {
    private final CalculationService calculationService;

    @RabbitListener(queues = RabbitMQConfig.notificationQueue)
    public void consumer(String personId) {
        log.info("Consumed {} from queue ", personId);
        calculationService.calculateTaxAndPost(personId);
    }
}
