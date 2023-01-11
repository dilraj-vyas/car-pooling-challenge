package com.cabify.carpooling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy
public class CarPoolingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarPoolingApplication.class, args);
    }

}
