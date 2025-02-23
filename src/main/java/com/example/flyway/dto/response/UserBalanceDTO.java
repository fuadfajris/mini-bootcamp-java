package com.example.flyway.dto.response;

import java.math.BigDecimal;

public interface UserBalanceDTO {
    Long getUserId();
    BigDecimal getTotalBalance();
}
