package com.example.currencychecker.service;

import com.example.currencychecker.api.controller.dto.response.OpenexchangeDtoResponse.*;
import com.example.currencychecker.api.controller.exception.CurrencyNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final OpenexchangeClient openexchangeClient;

    public CurrencyService(OpenexchangeClient openexchangeClient) {
        this.openexchangeClient = openexchangeClient;
    }

    public Currency getDifference(String name) {
        return openexchangeClient.getLatest().getRates().stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);
    }

}
