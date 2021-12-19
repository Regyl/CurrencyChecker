package com.example.currencychecker.api.controller.dto.response;

import com.example.currencychecker.config.MapDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OpenexchangeDtoResponse {

    private HashMap<String, Float> rates;
    //TODO: Оптимизировать (возможно PhantomReference, вытягивая лишь одно значение из всех
}
