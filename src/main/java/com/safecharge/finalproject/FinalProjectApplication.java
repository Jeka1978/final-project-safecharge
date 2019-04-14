package com.safecharge.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FinalProjectApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FinalProjectApplication.class, args);
    }

}
