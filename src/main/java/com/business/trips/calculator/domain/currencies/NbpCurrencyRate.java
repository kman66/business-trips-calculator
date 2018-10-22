package com.business.trips.calculator.domain.currencies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "CURRENCIES")
public class NbpCurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "currencies_seq")
    @SequenceGenerator(name = "currencies_seq", sequenceName = "public.currencies_id_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "CODE", unique = true, updatable = true)
    private String code;

    @Column(name = "MID")
    private BigDecimal mid;

    @Column(name = "CURRENCY_NAME")
    private String currencyName;

    public NbpCurrencyRate(String code, BigDecimal mid) {
        this.code = code;
        this.mid = mid;
    }

    public NbpCurrencyRate(String code, BigDecimal mid, String currencyName) {
        this.code = code;
        this.mid = mid;
        this.currencyName = currencyName;
    }
}
