package com.teamC.income;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient // added to enable service discovery using Consul instead of Eureka
@EnableFeignClients(
        basePackages = "com.teamC.clients"
)
public class IncomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncomeApplication.class, args);
    }
}
