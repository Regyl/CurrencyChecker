package com.example.currencychecker.service;

import com.example.currencychecker.controller.dto.response.OpenexchangeDtoResponse;
import com.example.currencychecker.exception.CurrencyNotFoundException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Log
public class CurrencyService {

    private final OpenexchangeClient openexchangeClient;

    public CurrencyService(OpenexchangeClient openexchangeClient) {
        this.openexchangeClient = openexchangeClient;
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

        log.info("new value " + newValue.toString());
        log.info("old value " + oldValue.toString());

        return newValue > oldValue;
    }

}
