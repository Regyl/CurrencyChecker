package com.example.currencychecker.api.controller.dto.response;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class OpenexchangeDtoResponse {
    List<HashMap<String, Integer>> rates;
}
