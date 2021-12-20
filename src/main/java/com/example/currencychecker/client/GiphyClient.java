package com.example.currencychecker.service;

import com.example.currencychecker.config.GiphyConfiguration;
import com.example.currencychecker.controller.dto.response.GiphyDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${giphy.url}", name = "${giphy.name}", configuration = GiphyConfiguration.class)
public interface GiphyClient {

    @GetMapping("${giphy.additional-url}")
    GiphyDtoResponse getGif(@RequestParam("api_key") String apiKey,
                            @RequestParam("q") String searchWord,
                            @RequestParam int limit);
}
