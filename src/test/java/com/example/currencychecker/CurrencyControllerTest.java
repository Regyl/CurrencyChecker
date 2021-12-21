package com.example.currencychecker;

import com.example.currencychecker.controller.CurrencyController;
import com.example.currencychecker.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.PostConstruct;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    private static final String RESPONSE_URL = "https://media4.giphy.com/media/5885nYOgBHdCw/giphy.gif?cid=9cdefce2wj71d6g2be2l8im3rygncos6ngst8am5ffgidpjc&rid=giphy.gif&ct=g";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyService service;

    @PostConstruct
    void setup() {
        when(service.getGif(anyString())).thenReturn(RESPONSE_URL);
    }

    @Test
    void testGetDifference() throws Exception {
        mvc.perform(get("/currencies/difference").param("name", "STN"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(RESPONSE_URL));

        mvc.perform(get("/currencies/difference"))
                .andExpect(status().isBadRequest());
    }

}
