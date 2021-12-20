package com.example.currencychecker.service;

import com.example.currencychecker.config.OpenexchangeConfiguration;
import com.example.currencychecker.controller.dto.response.OpenexchangeDtoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(url = "${openexchange.url}", name = "${openexchange.name}", configuration = OpenexchangeConfiguration.class)
public interface OpenexchangeClient {

    @GetMapping("${openexchange.latest-url}") //TODO: add const base from app.props
    OpenexchangeDtoResponse getLatest();

    @GetMapping("${openexchange.historical-url}")
    OpenexchangeDtoResponse getHistorical(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
