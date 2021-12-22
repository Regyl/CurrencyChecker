package com.example.currencychecker.controller;

import com.example.currencychecker.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Pattern;

@Tag(name = "Currency")

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/difference")
    @Operation(summary = "Check difference")
    public ModelAndView getDifference(
            @RequestParam @Pattern(regexp = "[A-Z]{3}") String name) {
        String url = service.getGif(name);
        return new ModelAndView("redirect:"+url);
    }
}
