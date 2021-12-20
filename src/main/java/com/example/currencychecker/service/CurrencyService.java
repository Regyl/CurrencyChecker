package com.example.currencychecker.service;

import com.example.currencychecker.exception.CurrencyNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
public class CurrencyService {

    //Вынесена по требованиям в настройки, но не используется, т.к. нужна платная подписка на openexchange
    private final String baseCurrency;
    private final OpenexchangeClient openexchangeClient;

    public CurrencyService(OpenexchangeClient openexchangeClient,
                           @Value("${openexchange.base-currency}") String baseCurrency) {
        this.openexchangeClient = openexchangeClient;
        this.baseCurrency = baseCurrency;
    }

    public MultipartFile getGif(String name) {
        if(isNewValueBigger(name)) {

        } else {

        }
        return null;
    }

    protected boolean isNewValueBigger(String name) {
        Float newValue = openexchangeClient.getLatest()
                        .getRates()
                        .get(name);

        if(newValue == null) {
            throw new CurrencyNotFoundException();
        }

        Float oldValue = openexchangeClient.getHistorical(LocalDate.now().minusDays(1))
                        .getRates()
                        .get(name);

        return newValue > oldValue;
    }

}
