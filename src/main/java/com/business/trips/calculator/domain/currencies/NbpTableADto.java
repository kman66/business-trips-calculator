package com.business.trips.calculator.domain.currencies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpTableADto {
    private String table;
    private String no;
    private String effectiveDate;

    @JsonProperty("rates")
    private List<NbpCurrencyRateDto> currencyRateDtos;
}
