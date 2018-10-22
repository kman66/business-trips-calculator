package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import com.business.trips.calculator.domain.currencies.NbpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryRepositoryTestSuite {

    private final static Logger LOGGER = LoggerFactory.getLogger(CountryRepositoryTestSuite.class);

    private NbpCurrencyRate nbpCurrencyRate;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private NbpRepository nbpRepository;

    private List<Country> createCountries() {
        nbpCurrencyRate = new NbpCurrencyRate(
                "ZZZ",
                BigDecimal.valueOf(2.12345).setScale(5),
                "Test Currency");
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Country 1", nbpCurrencyRate));
        countries.add(new Country("Country 2"));
        countries.add(new Country("Country 3", nbpCurrencyRate));
        return countries;
    }

    @Test
    public void shouldSaveCountry() {
        //Given
        Country country = createCountries().get(0);
        nbpRepository.save(nbpCurrencyRate);
        //When
        countryRepository.save(country);
        //Then
        Long countryId = country.getId();
        String countryName = country.getName();
        Country fetchedCountry = countryRepository.findOne(countryId);
        assertNotNull(fetchedCountry);
        assertEquals(countryId, fetchedCountry.getId());
        assertEquals(countryName, fetchedCountry.getName());
        assertTrue(fetchedCountry.getNbpCurrencyRate() instanceof NbpCurrencyRate);
        assertEquals(nbpCurrencyRate.getId(), fetchedCountry.getNbpCurrencyRate().getId());
        assertEquals(nbpCurrencyRate.getCode(), fetchedCountry.getNbpCurrencyRate().getCode());
        assertEquals(nbpCurrencyRate.getMid(), fetchedCountry.getNbpCurrencyRate().getMid());
        assertEquals(nbpCurrencyRate.getCurrencyName(), fetchedCountry.getNbpCurrencyRate().getCurrencyName());
        //Clean up
        try {
            countryRepository.delete(country.getId());
            nbpRepository.delete(nbpCurrencyRate.getId());
        } catch (Exception e) {
            LOGGER.error("Error during deleting currency.", e);
        }
    }

    @Test
    public void shouldFindById() {
        //Given
        List<Country> countryList = createCountries();
        nbpRepository.save(nbpCurrencyRate);
        countryRepository.save(countryList);
        Country country = countryList.get(2);
        //When
        Optional<Country> optionalCountry = countryRepository.findById(country.getId());
        //Then
        assertNotNull(optionalCountry.get());
        assertTrue(optionalCountry.get() instanceof Country);
        assertEquals(country.getId(), optionalCountry.get().getId());
        assertEquals(country.getName(), optionalCountry.get().getName());
        assertTrue(optionalCountry.get().getNbpCurrencyRate() instanceof NbpCurrencyRate);
        assertEquals(nbpCurrencyRate.getId(), optionalCountry.get().getNbpCurrencyRate().getId());
        assertEquals(nbpCurrencyRate.getCode(), optionalCountry.get().getNbpCurrencyRate().getCode());
        assertEquals(nbpCurrencyRate.getMid(), optionalCountry.get().getNbpCurrencyRate().getMid());
        assertEquals(nbpCurrencyRate.getCurrencyName(), optionalCountry.get().getNbpCurrencyRate().getCurrencyName());
        //Clean up
        try {
            countryRepository.delete(countryList);
            nbpRepository.delete(nbpCurrencyRate.getId());
        } catch (Exception e) {
            LOGGER.error("Error during deleting currency.", e);
        }
    }

    @Test
    public void shouldFindByName() {
        //Given
        List<Country> countryList = createCountries();
        nbpRepository.save(nbpCurrencyRate);
        countryRepository.save(countryList);
        Country country = countryList.get(2);
        //When
        Optional<Country> optionalCountry = countryRepository.findByName("Country 3");
        //Then
        assertNotNull(optionalCountry.get());
        assertTrue(optionalCountry.get() instanceof Country);
        assertEquals(country.getId(), optionalCountry.get().getId());
        assertEquals(country.getName(), optionalCountry.get().getName());
        assertTrue(optionalCountry.get().getNbpCurrencyRate() instanceof NbpCurrencyRate);
        assertEquals(nbpCurrencyRate.getId(), optionalCountry.get().getNbpCurrencyRate().getId());
        assertEquals(nbpCurrencyRate.getCode(), optionalCountry.get().getNbpCurrencyRate().getCode());
        assertEquals(nbpCurrencyRate.getMid(), optionalCountry.get().getNbpCurrencyRate().getMid());
        assertEquals(nbpCurrencyRate.getCurrencyName(), optionalCountry.get().getNbpCurrencyRate().getCurrencyName());
        //Clean up
        try {
            countryRepository.delete(countryList);
            nbpRepository.delete(nbpCurrencyRate.getId());
        } catch (Exception e) {
            LOGGER.error("Error during deleting currency.", e);
        }
    }
}