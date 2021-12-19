package com.example.currencychecker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request currency doesn't exist")
public class CurrencyNotFoundException extends RuntimeException {
}
