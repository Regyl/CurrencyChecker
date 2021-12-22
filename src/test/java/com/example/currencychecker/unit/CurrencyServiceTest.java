package com.example.currencychecker.unit;

import com.example.currencychecker.client.GiphyClient;
import com.example.currencychecker.client.OpenExchangeClient;
import com.example.currencychecker.client.dto.response.GiphyDtoResponse;
import com.example.currencychecker.client.dto.response.OpenExchangeDtoResponse;
import com.example.currencychecker.exception.CurrencyNotFoundException;
import com.example.currencychecker.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class CurrencyServiceTest {

    private static final String VALID_CURRENCY = "STN";

    @Autowired
    private CurrencyService service;

    @MockBean
    private OpenExchangeClient openexchangeClient;
    @MockBean
    private GiphyClient giphyClient;

    @PostConstruct
    void setup() {
        GiphyDtoResponse response = new GiphyDtoResponse();
        response.setData(new ArrayList<>(1));
        when(giphyClient.getGif(anyString(),anyString(), anyInt())).thenReturn(response);
    }


    @Test
    void invalidOpenExchangeResponse() {

        OpenExchangeDtoResponse invalidResponse = new OpenExchangeDtoResponse();
        when(openexchangeClient.getLatest()).thenReturn(invalidResponse);

        assertDoesNotThrow( () -> service.getGif(VALID_CURRENCY));
    }

    @Test
    void validOpenExchangeResponse() {

    }
}
