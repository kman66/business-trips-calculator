package com.business.trips.calculator.domain.currencies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NbpCurrencyRate {
    private Long id;
    private String code;
    private BigDecimal mid;
    private LocalDateTime fetchedOn;
}
