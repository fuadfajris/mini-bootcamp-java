package com.example.flyway.component;

import com.example.flyway.dto.response.CurrencyResponseDTO;
import com.example.flyway.service.procedur.CurrencyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Component
public class CurrencyScheduler {

    @Autowired
    private CurrencyProducer currencyProducer;

    @Scheduled(fixedRate = 120000) // Eksekusi setiap 2 menit
    public void fetchAndSendCurrencyData() {
        currencyProducer.sendCurrencyData("EUR");
    }
}

