package com.example.currencychecker.api.controller;

import com.example.currencychecker.api.controller.dto.response.OpenexchangeDtoResponse;
import com.example.currencychecker.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;

@Tag(name = "Currency")

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/difference")
    @Operation(summary = "Check difference")
    public OpenexchangeDtoResponse.Currency getDifference(
            @RequestParam @Pattern(regexp = "[A-Z]{3}") String name) {
        return service.getDifference(name);
    }
}
