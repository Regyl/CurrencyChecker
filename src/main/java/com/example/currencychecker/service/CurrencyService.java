package com.example.currencychecker.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CurrencyService {

    private final OpenexchangeClient openexchangeClient;

    public CurrencyService(OpenexchangeClient openexchangeClient) {
        this.openexchangeClient = openexchangeClient;
    }

    public List<HashMap<String, Integer>> getLatest() {
        return openexchangeClient.getLatest().getRates();
    }

}
