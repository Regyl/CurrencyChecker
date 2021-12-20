package com.example.currencychecker.controller;

import com.example.currencychecker.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;

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
    public MultipartFile getDifference(
            @RequestParam @Pattern(regexp = "[A-Z]{3}") String name) {
        return service.getGif(name);
    }
}
