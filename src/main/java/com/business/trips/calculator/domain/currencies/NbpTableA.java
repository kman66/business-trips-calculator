package com.business.trips.calculator.domain.currencies;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class NbpTableA {
    private String table;
    private String no;
    private String effectiveDate;
    private List<NbpCurrencyRate> currencyRates;
}
