package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import com.business.trips.calculator.domain.currencies.NbpCurrencyRateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryMapperTestSuite {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    public void shouldMapToCountry() {
        //Given
        NbpCurrencyRateDto nbpCurrencyRateDto = new NbpCurrencyRateDto(
                1L, "ZZZ",
                BigDecimal.valueOf(1.00021).setScale(5),
                "Currency ZZZ");
        CountryDto countryDto = new CountryDto(1L, "Test Country", nbpCurrencyRateDto);
        //When
        Country mappedCountry = countryMapper.mapToCountry(countryDto);
        //Then
        assertNotNull(mappedCountry);
        assertTrue(mappedCountry instanceof Country);
        assertEquals(1L, mappedCountry.getId(), 0);
        assertEquals("Test Country", mappedCountry.getName());
        assertTrue(mappedCountry.getNbpCurrencyRate() instanceof NbpCurrencyRate);
        assertEquals(1L, mappedCountry.getNbpCurrencyRate().getId(), 0);
        assertEquals("ZZZ", mappedCountry.getNbpCurrencyRate().getCode());
        assertEquals(BigDecimal.valueOf(1.00021).setScale(5), mappedCountry.getNbpCurrencyRate().getMid());
        assertEquals("Currency ZZZ", mappedCountry.getNbpCurrencyRate().getCurrencyName());
    }

    @Test
    public void shouldMapToCountryDto() {
        //Given
        NbpCurrencyRate nbpCurrencyRate = new NbpCurrencyRate(
                1L, "ZZZ",
                BigDecimal.valueOf(1.00021).setScale(5),
                "Currency ZZZ");
        Country country = new Country(1L, "Test Country", nbpCurrencyRate);
        //When
        CountryDto mappedCountryDto = countryMapper.mapToCountryDto(country);
        //Then
        assertNotNull(mappedCountryDto);
        assertTrue(mappedCountryDto instanceof CountryDto);
        assertEquals(1L, mappedCountryDto.getId(), 0);
        assertEquals("Test Country", mappedCountryDto.getName());
        assertTrue(mappedCountryDto.getNbpCurrencyRateDto() instanceof NbpCurrencyRateDto);
        assertEquals(1L, mappedCountryDto.getNbpCurrencyRateDto().getId(), 0);
        assertEquals("ZZZ", mappedCountryDto.getNbpCurrencyRateDto().getCode());
        assertEquals(BigDecimal.valueOf(1.00021).setScale(5), mappedCountryDto.getNbpCurrencyRateDto().getMid());
        assertEquals("Currency ZZZ", mappedCountryDto.getNbpCurrencyRateDto().getCurrencyName());
    }

    @Test
    public void shouldMapToCountryDtoList() {
        //Given
        NbpCurrencyRate nbpCurrencyRate = new NbpCurrencyRate(
                1L, "ZZZ",
                BigDecimal.valueOf(1.00021).setScale(5),
                "Currency ZZZ");
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country(1L, "Country 1", nbpCurrencyRate));
        countryList.add(new Country(4L, "Country 2", nbpCurrencyRate));
        //When
        List<CountryDto> countryDtoList = countryMapper.mapToCountryDtoList(countryList);
        //Then
        assertEquals(2, countryDtoList.size());
        assertTrue(countryDtoList.get(0) instanceof CountryDto);
        assertEquals(1L, countryDtoList.get(0).getId(), 0);
        assertEquals("Country 1", countryDtoList.get(0).getName());
        assertTrue(countryDtoList.get(0).getNbpCurrencyRateDto() instanceof NbpCurrencyRateDto);
        assertEquals(1L, countryDtoList.get(0).getNbpCurrencyRateDto().getId(), 0);
        assertEquals("ZZZ", countryDtoList.get(0).getNbpCurrencyRateDto().getCode());
        assertEquals(BigDecimal.valueOf(1.00021).setScale(5), countryDtoList.get(0).getNbpCurrencyRateDto().getMid());
        assertEquals("Currency ZZZ", countryDtoList.get(0).getNbpCurrencyRateDto().getCurrencyName());
        assertTrue(countryDtoList.get(1) instanceof CountryDto);
        assertEquals(4L, countryDtoList.get(1).getId(), 0);
        assertEquals("Country 2", countryDtoList.get(1).getName());
        assertTrue(countryDtoList.get(1).getNbpCurrencyRateDto() instanceof NbpCurrencyRateDto);
        assertEquals(1L, countryDtoList.get(1).getNbpCurrencyRateDto().getId(), 0);
        assertEquals("ZZZ", countryDtoList.get(1).getNbpCurrencyRateDto().getCode());
        assertEquals(BigDecimal.valueOf(1.00021).setScale(5), countryDtoList.get(1).getNbpCurrencyRateDto().getMid());
        assertEquals("Currency ZZZ", countryDtoList.get(1).getNbpCurrencyRateDto().getCurrencyName());
    }
}