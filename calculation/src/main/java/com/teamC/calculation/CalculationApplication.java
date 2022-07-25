package com.teamC.calculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(
        scanBasePackages = {
                "com.teamC.calculation",
                "com.teamC.amqp"
        }
)
//@EnableEurekaClient
@EnableDiscoveryClient // added to enable service discovery using Consul instead of Eureka
@EnableFeignClients(
        basePackages = "com.teamC.clients"
)

public class CalculationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculationApplication.class, args);
    }
}
