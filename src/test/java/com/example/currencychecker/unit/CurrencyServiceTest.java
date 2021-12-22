package com.example.currencychecker.unit;

import com.example.currencychecker.client.GiphyClient;
import com.example.currencychecker.client.OpenExchangeClient;
import com.example.currencychecker.client.dto.response.GiphyDtoResponse;
import com.example.currencychecker.client.dto.response.OpenExchangeDtoResponse;
import com.example.currencychecker.exception.CurrencyNotFoundException;
import com.example.currencychecker.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class CurrencyServiceTest {

    private static final String RESPONSE_URL = "https://media4.giphy.com/media/5885nYOgBHdCw/giphy.gif?cid=9cdefce2wj71d6g2be2l8im3rygncos6ngst8am5ffgidpjc&rid=giphy.gif&ct=g";
    private static final String VALID_CURRENCY = "STN";

    @Autowired
    private CurrencyService service;

    @MockBean
    private OpenExchangeClient openexchangeClient;
    @MockBean
    private GiphyClient giphyClient;

    private final OpenExchangeDtoResponse openExchangeDtoResponse;
    private final GiphyDtoResponse giphyDtoResponse;

    //build OpenExchangeDtoResponse
    {
        HashMap<String, Float> rates = new HashMap<>(2);
        rates.put(VALID_CURRENCY, 2.607f);

        openExchangeDtoResponse = new OpenExchangeDtoResponse();
        openExchangeDtoResponse.setRates(rates);
    }

    //build GiphyDtoResponse
    {
        GiphyDtoResponse.GeneralData.Image.Original original = new GiphyDtoResponse.GeneralData.Image.Original();
        original.setUrl(RESPONSE_URL);

        GiphyDtoResponse.GeneralData.Image image = new GiphyDtoResponse.GeneralData.Image();
        image.setOriginal(original);

        GiphyDtoResponse.GeneralData generalData = new GiphyDtoResponse.GeneralData();
        generalData.setImages(image);

        giphyDtoResponse = new GiphyDtoResponse();
        giphyDtoResponse.setData(new ArrayList<>(List.of(generalData)));
    }

    @PostConstruct
    void setup() {
        when(giphyClient.getGif(anyString(),anyString(), anyInt())).thenReturn(giphyDtoResponse);

        when(openexchangeClient.getLatest()).thenReturn(openExchangeDtoResponse);
        when(openexchangeClient.getHistorical(any(LocalDate.class))).thenReturn(openExchangeDtoResponse);
    }


    @Test
    void invalidOpenExchangeResponse() {
        assertThrows(CurrencyNotFoundException.class, () -> service.getGif("ABC"));
    }

    @Test
    void validOpenExchangeResponse() {
        assertEquals(RESPONSE_URL, service.getGif(VALID_CURRENCY));
    }
}
