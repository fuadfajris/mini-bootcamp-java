package com.example.flyway.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class CurrencyResponseDTO {
    private String date;
    private String base;
    private Map<String, String> rates;
}
