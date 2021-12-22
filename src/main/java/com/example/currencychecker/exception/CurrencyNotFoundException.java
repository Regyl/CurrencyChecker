package com.example.currencychecker.exception;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(String currencyName) {
        super(String.format("Request currency %s doesn't exists", currencyName));
    }
}
