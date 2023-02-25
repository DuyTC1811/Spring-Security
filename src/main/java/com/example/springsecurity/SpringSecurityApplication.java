package com.example.springsecurity;

import io.cqrs.dispascher.ISpringBus;
import io.cqrs.dispascher.Registry;
import io.cqrs.dispascher.SpringBusImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    public Registry registry(ApplicationContext applicationContext) {
        return new Registry(applicationContext);
    }

    @Bean
    public ISpringBus commandBus(Registry registry) {
        return new SpringBusImpl(registry);
    }
}
