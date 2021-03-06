package com.example.currencychecker.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenexchangeConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor(@Value("${openexchange.api-key}") String token) {
        return template -> template.header("Authorization", "Token " + token);
    }
}
