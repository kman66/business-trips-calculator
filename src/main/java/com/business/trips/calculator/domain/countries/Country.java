package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import com.business.trips.calculator.domain.projects.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(
            targetEntity = Project.class,
            mappedBy = "country",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Project> projectList;

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, NbpCurrencyRate nbpCurrencyRate) {
        this.name = name;
        this.nbpCurrencyRate = nbpCurrencyRate;
    }

    public Country(Long id, String name, NbpCurrencyRate nbpCurrencyRate) {
        this.id = id;
        this.name = name;
        this.nbpCurrencyRate = nbpCurrencyRate;
    }
}
