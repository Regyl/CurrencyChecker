package com.example.currencychecker.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static final List<String> paramList = List.of(
            "STn", "StN",
            "sTN", "", "ST",
            "S", "s"
    );

    @Test
    void test() throws Exception {
        for(String param : paramList) {
            mvc.perform(get("/currencies/difference").param("name", param))
                    .andExpect(status().isBadRequest());
        }
    }
}
