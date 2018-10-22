package com.business.trips.calculator.domain.currencies;

import com.business.trips.calculator.domain.countries.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    @OneToMany(
            targetEntity = Country.class,
            mappedBy = "nbpCurrencyRate",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Country> countries;

    public NbpCurrencyRate(String code, BigDecimal mid) {
        this.code = code;
        this.mid = mid;
    }

    public NbpCurrencyRate(String code, BigDecimal mid, String currencyName) {
        this.code = code;
        this.mid = mid;
        this.currencyName = currencyName;
    }

    public NbpCurrencyRate(Long id, String code, BigDecimal mid, String currencyName) {
        this.id = id;
        this.code = code;
        this.mid = mid;
        this.currencyName = currencyName;
    }
}
