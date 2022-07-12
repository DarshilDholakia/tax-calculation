package com.teamC.income;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IncomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncomeApplication.class, args);
    }
}
