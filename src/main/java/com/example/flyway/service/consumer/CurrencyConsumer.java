package com.example.flyway.service.consumer;

import com.example.flyway.dto.response.CurrencyResponseDTO;
import com.example.flyway.model.Currency;
import com.example.flyway.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CurrencyConsumer {
    @Autowired
    private CurrencyRepository currencyRepository;

    @KafkaListener(topics = "currency", groupId = "currency-consumer-group")
    public void consume(CurrencyResponseDTO message) {
        try {
            System.out.println("Received currency data: " + message);
            if (message.getRates() != null && !message.getRates().isEmpty()) {
                for (Map.Entry<String, String> entry : message.getRates().entrySet()) {
                    Currency data = new Currency();
                    data.setExchangeDate(message.getDate());
                    data.setBase(message.getBase());
                    data.setCurrencyCode(entry.getKey());
                    data.setCurrencyRate(entry.getValue());

                    currencyRepository.save(data);
                    System.out.println("Currency data saved to DB: " + entry.getKey() + " - " + entry.getValue());
                }
            }
        } catch (Exception e) {
            System.err.println("Error in Kafka listener: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
