package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRateDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CountryDto {
    private Long id;
    private String name;
    @JsonProperty(value = "currency")
    private NbpCurrencyRateDto nbpCurrencyRateDto;
}
