package com.business.trips.calculator.domain.currencies;

import com.business.trips.calculator.domain.parameters.Parameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NbpDbServiceTestSuite {

    private static final String FETCHED_CURRENCIES_EFFECTIVE_DATE = "fetched_currencies_effective_date";

    @Autowired
    private NbpDbService nbpDbService;

    @Test
    public void shouldFetchCurrenciesFromTableA() {
        //Given
        List<NbpCurrencyRateDto> nbpCurrencyRateDtoList = new ArrayList<>();
        nbpCurrencyRateDtoList.add(new NbpCurrencyRateDto(1L, "AAA", BigDecimal.valueOf(0.03450).setScale(5), "Currency AAA"));
        nbpCurrencyRateDtoList.add(new NbpCurrencyRateDto(2L, "ZZZ", BigDecimal.valueOf(3.10059).setScale(5), "Currency ZZZ"));
        List<NbpTableADto> nbpTableADtoList = new ArrayList<>();
        nbpTableADtoList.add(new NbpTableADto("A", "100", "2018-10-22", nbpCurrencyRateDtoList));
        //When
        List<NbpCurrencyRateDto> fetchedNbpCurrencyRateDtoList = nbpDbService.fetchCurrenciesFromTableA(nbpTableADtoList);
        //Then
        assertNotNull(fetchedNbpCurrencyRateDtoList);
        assertEquals(2, fetchedNbpCurrencyRateDtoList.size());
        assertTrue(fetchedNbpCurrencyRateDtoList.get(0) instanceof NbpCurrencyRateDto);
        assertEquals(1L, fetchedNbpCurrencyRateDtoList.get(0).getId(), 0);
        assertEquals("AAA", fetchedNbpCurrencyRateDtoList.get(0).getCode());
        assertEquals(BigDecimal.valueOf(0.03450).setScale(5), fetchedNbpCurrencyRateDtoList.get(0).getMid());
        assertEquals("Currency AAA", fetchedNbpCurrencyRateDtoList.get(0).getCurrencyName());
        assertTrue(fetchedNbpCurrencyRateDtoList.get(1) instanceof NbpCurrencyRateDto);
        assertEquals(2L, fetchedNbpCurrencyRateDtoList.get(1).getId(), 0);
        assertEquals("ZZZ", fetchedNbpCurrencyRateDtoList.get(1).getCode());
        assertEquals(BigDecimal.valueOf(3.10059).setScale(5), fetchedNbpCurrencyRateDtoList.get(1).getMid());
        assertEquals("Currency ZZZ", fetchedNbpCurrencyRateDtoList.get(1).getCurrencyName());
    }

    @Test
    public void shouldReturnCorrectNameOfFetchedCurrenciesEffectiveDateParameter() {
        //Given
        List<NbpTableADto> nbpTableADtoList = new ArrayList<>();
        nbpTableADtoList.add(new NbpTableADto("Test table", "1", "2018-10-19", new ArrayList<>()));
        //When
        Parameter parameter = nbpDbService.checkIfEffectiveDateInDbAndReturn(nbpTableADtoList);
        //Then
        assertNotNull(parameter);
        assertEquals(FETCHED_CURRENCIES_EFFECTIVE_DATE, parameter.getName());
    }
}