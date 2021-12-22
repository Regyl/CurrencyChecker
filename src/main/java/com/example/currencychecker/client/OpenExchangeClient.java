package com.example.currencychecker.client;

import com.example.currencychecker.config.OpenexchangeConfiguration;
import com.example.currencychecker.client.dto.response.OpenExchangeDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(url = "${openexchange.url}", name = "${openexchange.name}", configuration = OpenexchangeConfiguration.class)
public interface OpenExchangeClient {

    @GetMapping("${openexchange.latest-url}")
    OpenExchangeDtoResponse getLatest();

    @GetMapping("${openexchange.historical-url}")
    OpenExchangeDtoResponse getHistorical(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
