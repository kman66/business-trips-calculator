package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import com.business.trips.calculator.domain.currencies.NbpCurrencyRateDto;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryDbServiceTestSuite {

    @InjectMocks
    private CountryDbService countryDbService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionByExtractingCountry() {
        //Given
        thrown.expect(CountryNotFoundException.class);
        Mockito.when(countryRepository.findById(1L)).thenThrow(new CountryNotFoundException());
        //When
        countryDbService.extractCountryFromDbOrThrowException(1L);
        //Then
        fail("This method should throw the CountryNotFoundException");
    }

    @Test
    public void shouldExtractCountryFromDb() {
        //Given
        Country country = new Country(1L, "Test Country", new NbpCurrencyRate());
        Optional<Country> optionalCountry = Optional.of(country);
        CountryDto countryDto = new CountryDto(1L, "Test Country", new NbpCurrencyRateDto());
        Mockito.when(countryRepository.findById(1L)).thenReturn(optionalCountry);
        Mockito.when(countryMapper.mapToCountryDto(optionalCountry.get())).thenReturn(countryDto);
        //When
        CountryDto extractedCountryDto = countryDbService.extractCountryFromDbOrThrowException(1L);
        //Then
        assertNotNull(extractedCountryDto);
        assertTrue(extractedCountryDto instanceof CountryDto);
        assertEquals(1L, extractedCountryDto.getId(), 0);
        assertEquals("Test Country", extractedCountryDto.getName());
        assertNotNull(extractedCountryDto.getNbpCurrencyRateDto());
        assertTrue(extractedCountryDto.getNbpCurrencyRateDto() instanceof NbpCurrencyRateDto);
    }
}