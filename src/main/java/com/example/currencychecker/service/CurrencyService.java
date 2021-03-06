package com.example.currencychecker.service;

import com.example.currencychecker.client.GiphyClient;
import com.example.currencychecker.client.OpenExchangeClient;
import com.example.currencychecker.exception.CurrencyNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrencyService {

    private final OpenExchangeClient openexchangeClient;
    private final GiphyClient giphyClient;

    private final String baseCurrency; //Вынесена по требованиям в настройки, но не используется, т.к. нужна платная подписка на openexchange
    private final String giphyApiKey;
    private final String giphySuccessWord;
    private final String giphyFailureWord;
    private final Integer giphyLimitPerRequest;

    public CurrencyService(OpenExchangeClient openexchangeClient, GiphyClient giphyClient,
                           @Value("${openexchange.base-currency}") String baseCurrency,
                           @Value("${giphy.api-key}") String giphyApiKey,
                           @Value("${giphy.success-word}") String giphySuccessWord,
                           @Value("${giphy.failure-word}") String giphyFailureWord,
                           @Value("${giphy.limit-per-request}") Integer giphyLimitPerRequest) {
        this.openexchangeClient = openexchangeClient;
        this.baseCurrency = baseCurrency;
        this.giphyApiKey = giphyApiKey;
        this.giphyClient = giphyClient;
        this.giphySuccessWord = giphySuccessWord;
        this.giphyFailureWord = giphyFailureWord;
        this.giphyLimitPerRequest = giphyLimitPerRequest;
    }

    public String getGif(String name) {
        if(isNewValueBigger(name)) {
            return giphyClient.getGif(giphyApiKey, giphySuccessWord, giphyLimitPerRequest)
                    .getFirstOriginalUrl();
        } else {
            return giphyClient.getGif(giphyApiKey, giphyFailureWord, giphyLimitPerRequest)
                    .getFirstOriginalUrl();
        }
    }

    private boolean isNewValueBigger(String name) {
        Float newValue = openexchangeClient.getLatest()
                        .getRates()
                        .get(name);

        if(newValue == null) {
            throw new CurrencyNotFoundException(name);
        }

        Float oldValue = openexchangeClient.getHistorical(LocalDate.now().minusDays(1))
                        .getRates()
                        .get(name);

        return newValue > oldValue;
    }

}
