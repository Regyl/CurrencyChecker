package com.example.currencychecker.client.dto.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class OpenExchangeDtoResponse {

    private HashMap<String, Float> rates;

}
