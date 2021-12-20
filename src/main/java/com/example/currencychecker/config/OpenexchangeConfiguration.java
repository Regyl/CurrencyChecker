package com.example.currencychecker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
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
