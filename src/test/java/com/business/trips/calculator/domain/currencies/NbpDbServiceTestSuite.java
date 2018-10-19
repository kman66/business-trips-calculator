package com.business.trips.calculator.domain.currencies;

import com.business.trips.calculator.domain.parameters.Parameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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