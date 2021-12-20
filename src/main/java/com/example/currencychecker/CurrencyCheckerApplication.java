package com.example.currencychecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CurrencyCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyCheckerApplication.class, args);
    }

}
