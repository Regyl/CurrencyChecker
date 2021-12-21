package com.example.currencychecker.unit;

import com.example.currencychecker.client.GiphyClient;
import com.example.currencychecker.client.OpenexchangeClient;
import com.example.currencychecker.client.dto.response.GiphyDtoResponse;
import com.example.currencychecker.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.annotation.PostConstruct;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.properties")
class CurrencyServiceTest {

    @Autowired
    private CurrencyService service;
    @Autowired
    private PodamFactory podamFactory;

    @MockBean
    private OpenexchangeClient openexchangeClient;
    @MockBean
    private GiphyClient giphyClient;

    @PostConstruct
    void setup() {
        when(giphyClient.getGif(anyString(),anyString(), anyInt())).thenReturn(podamFactory.manufacturePojoWithFullData(GiphyDtoResponse.class));
    }

    @Test
    void test() {

    }
}
