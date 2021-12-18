package com.example.currencychecker.api.controller;

import com.example.currencychecker.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
    public List<HashMap<String, Integer>> getDifference() { //@RequestParam @Pattern(regexp = "${currency.pattern}") String name
        return service.getLatest();
    }
}
