package com.example.currencychecker.controller.dto.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class OpenexchangeDtoResponse {

    private HashMap<String, Float> rates;
    //TODO: Оптимизировать (возможно PhantomReference, вытягивая лишь одно значение из всех
}
