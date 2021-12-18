package com.example.currencychecker.service;

import com.example.currencychecker.config.OpenexchangeConfiguration;
import com.example.currencychecker.api.controller.dto.response.OpenexchangeDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@FeignClient(url = "${openexchange.url}", name = "${openexchange.name}", configuration = OpenexchangeConfiguration.class)
public interface OpenexchangeClient {

    @GetMapping("/latest.json")
    OpenexchangeDtoResponse getLatest();

    @GetMapping("/historical/{date}.json")
    List<HashMap<String, Integer>> getHistorical(@PathVariable LocalDate date);
}
