package com.example.currencychecker.client.dto.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class OpenexchangeDtoResponse {

    private HashMap<String, Float> rates;

}
