package com.example.currencychecker.service;

import com.example.currencychecker.api.controller.dto.response.OpenexchangeDtoResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CurrencyService {

    private final OpenexchangeClient openexchangeClient;

    public CurrencyService(OpenexchangeClient openexchangeClient) {
        this.openexchangeClient = openexchangeClient;
    }

    public OpenexchangeDtoResponse getDifference(String name) {
        return openexchangeClient.getLatest();
        /*return openexchangeClient.getLatest().getRates().stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);*/
    }

}
