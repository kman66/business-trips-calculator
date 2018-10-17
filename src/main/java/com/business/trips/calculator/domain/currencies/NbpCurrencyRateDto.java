package com.business.trips.calculator.domain.currencies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpCurrencyRateDto {
    private Long id;
    private String code;
    private BigDecimal mid;
    @JsonIgnore
    private LocalDateTime fetchedOn;

    public NbpCurrencyRateDto(Long id, String code, BigDecimal mid) {
        this.id = id;
        this.code = code;
        this.mid = mid;
    }
}
