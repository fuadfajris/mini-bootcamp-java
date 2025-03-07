package com.example.flyway.service.procedur;

import com.example.flyway.dto.response.CurrencyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyProducer {

    private static final String TOPIC = "currency";

    @Autowired
    private KafkaTemplate<String, CurrencyResponseDTO> kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;  // Inject RestTemplate as a bean

    public CurrencyResponseDTO sendCurrencyData(String symbols) {
        String apiKey = "5a8e127704c441dcb01e4b8e09d64107";
        String apiUrl = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey="
                + apiKey + "&symbols=" + symbols;

        try {
            ResponseEntity<CurrencyResponseDTO> response = restTemplate.getForEntity(apiUrl, CurrencyResponseDTO.class);

            System.out.println("Before sending data to Kafka");
            kafkaTemplate.send(TOPIC, response.getBody());
            System.out.println("After sending data to Kafka");
            return response.getBody();
        } catch (RestClientException e) {
            // Handle the error, log it, and potentially rethrow or handle accordingly
            System.err.println("Error fetching data from API: " + e.getMessage());
            return null;
        }
    }
}
