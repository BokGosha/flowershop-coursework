package com.example.flowershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FlowerShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowerShopApplication.class, args);
    }
}
