package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "COUNTRIES")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "countries_seq")
    @SequenceGenerator(name = "countries_seq", sequenceName = "public.countries_id_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    private NbpCurrencyRate nbpCurrencyRate;

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, NbpCurrencyRate nbpCurrencyRate) {
        this.name = name;
        this.nbpCurrencyRate = nbpCurrencyRate;
    }
}
