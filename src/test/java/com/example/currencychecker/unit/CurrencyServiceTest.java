package com.example.currencychecker.unit;

import com.example.currencychecker.client.GiphyClient;
import com.example.currencychecker.client.OpenExchangeClient;
import com.example.currencychecker.client.dto.response.GiphyDtoResponse;
import com.example.currencychecker.client.dto.response.OpenExchangeDtoResponse;
import com.example.currencychecker.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.annotation.PostConstruct;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.properties")
class CurrencyServiceTest {

    private static final String VALID_CURRENCY = "STN";

    private static PodamFactoryImpl podamFactory;

    private CurrencyService service;


    @MockBean
    private OpenExchangeClient openexchangeClient;
    @MockBean
    private GiphyClient giphyClient;

    @PostConstruct
    void setup() {
        when(giphyClient.getGif(anyString(),anyString(), anyInt())).thenReturn(podamFactory.manufacturePojoWithFullData(GiphyDtoResponse.class));
    }

    @Test
    void invalidOpenExchangeResponse() {
        OpenExchangeDtoResponse invalidResponse = podamFactory.manufacturePojoWithFullData(OpenExchangeDtoResponse.class);
        when(openexchangeClient.getLatest()).thenReturn(invalidResponse);

        assertThrows(ClassNotFoundException.class, () -> service.getGif(VALID_CURRENCY));
    }

    @Test
    void validOpenExchangeResponse() {

    }
}
