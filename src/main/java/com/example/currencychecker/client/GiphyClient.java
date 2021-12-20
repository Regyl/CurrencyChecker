package com.example.currencychecker.client;

import com.example.currencychecker.client.dto.response.GiphyDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${giphy.url}", name = "${giphy.name}")
public interface GiphyClient {

    @GetMapping("${giphy.search-url}")
    GiphyDtoResponse getGif(@RequestParam("api_key") String apiKey,
                            @RequestParam("q") String searchWord,
                            @RequestParam int limit);
}
