package com.example.currencychecker.api.controller.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OpenexchangeDtoResponse {

    List<Currency> rates;
    //TODO: Оптимизировать (возможно PhantomReference, вытягивая лишь одно значение из всех
    //Мб HashMap<"AOT", Object>

    @Data
    public static class Currency {
        private String name;

        private Float value;
    }
}
