package com.example.currencychecker.service;

import com.example.currencychecker.controller.dto.response.OpenexchangeDtoResponse;
import com.example.currencychecker.exception.CurrencyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {

    private final OpenexchangeClient openexchangeClient;

    public CurrencyService(OpenexchangeClient openexchangeClient) {
        this.openexchangeClient = openexchangeClient;
    }

    public Float getDifference(String name) {
        Optional<Float> newValue = Optional.ofNullable(
                openexchangeClient.getLatest()
                        .getRates()
                        .get(name));

        return newValue.orElseThrow(CurrencyNotFoundException::new);
    }

}
