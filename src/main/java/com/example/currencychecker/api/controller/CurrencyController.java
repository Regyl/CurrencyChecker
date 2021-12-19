package com.example.currencychecker.api.controller;

import com.example.currencychecker.api.controller.dto.response.OpenexchangeDtoResponse;
import com.example.currencychecker.config.MapDeserializer;
import com.example.currencychecker.service.CurrencyService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

@Tag(name = "Currency")

@RestController
@RequestMapping("/currencies")
@Log
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/difference")
    @Operation(summary = "Check difference")
    public OpenexchangeDtoResponse getDifference(
            @RequestParam @Pattern(regexp = "[A-Z]{3}") String name) {
        return service.getDifference(name);
    }
}
