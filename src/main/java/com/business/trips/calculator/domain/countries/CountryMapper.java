package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRateMapper;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    @Autowired
    private NbpCurrencyRateMapper nbpCurrencyRateMapper;

    public Country mapToCountry(final CountryDto countryDto) {
        return new Country(
                countryDto.getId(),
                countryDto.getName(),
                nbpCurrencyRateMapper.getMapperFacade().mapReverse(countryDto.getNbpCurrencyRateDto())
        );
    }

    public CountryDto mapToCountryDto(final Country country) {
        return new CountryDto(
                country.getId(),
                country.getName(),
                nbpCurrencyRateMapper.getMapperFacade().map(country.getNbpCurrencyRate())
        );
    }

    public List<CountryDto> mapToCountryDtoList(final List<Country> countryList) {
        return countryList.stream()
                .map(c -> mapToCountryDto(c))
                .collect(Collectors.toList());
    }
}
