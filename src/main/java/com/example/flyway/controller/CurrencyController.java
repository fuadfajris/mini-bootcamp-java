package com.example.flyway.controller;

import com.example.flyway.dto.response.CurrencyResponseDTO;
import com.example.flyway.service.procedur.CurrencyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    private CurrencyProducer currencyProducer;

    @GetMapping()
    public ResponseEntity<CurrencyResponseDTO> getCurrency(@RequestParam(defaultValue = "USD") String symbols) {
        CurrencyResponseDTO response = currencyProducer.sendCurrencyData(symbols);
        return ResponseEntity.ok(response);
    }
}
