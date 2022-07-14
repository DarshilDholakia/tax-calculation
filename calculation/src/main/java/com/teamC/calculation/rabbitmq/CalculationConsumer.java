package com.teamC.calculation.rabbitmq;

import com.teamC.calculation.CalculationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j // enables users to make logs to the console upon running the application
@AllArgsConstructor
public class CalculationConsumer {
    private final CalculationService calculationService;
    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(String personId) {
        log.info("Consumed {} from queue ", personId);
        try {
            TimeUnit.SECONDS.sleep(10); //delay by 10sec
            calculationService.calculateTaxAndPost(personId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
