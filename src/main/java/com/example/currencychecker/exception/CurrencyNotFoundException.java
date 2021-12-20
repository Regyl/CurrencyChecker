package com.example.currencychecker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(String currencyName) {
        super(String.format("Request currency %s doesn't exists", currencyName));
    }
}
