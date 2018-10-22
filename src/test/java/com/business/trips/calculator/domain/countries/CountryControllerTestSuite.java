package com.business.trips.calculator.domain.countries;

import com.business.trips.calculator.domain.currencies.NbpCurrencyRateDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryController.class)
public class CountryControllerTestSuite {

    private NbpCurrencyRateDto nbpCurrencyRateDto;

    private CountryDto countryDto;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryController countryController;

    @Before
    public void init() {
        nbpCurrencyRateDto = new NbpCurrencyRateDto(
                1L,
                "ZZZ",
                BigDecimal.valueOf(0.01234).setScale(5),
                "Test currency");
        countryDto = new CountryDto(1L, "Test Country", nbpCurrencyRateDto);
    }

    @Test
    public void shouldFetchCountries() throws Exception {
        //Given
        List<CountryDto> countryDtoList = new ArrayList<>();
        countryDtoList.add(countryDto);
        Mockito.when(countryController.getCountries()).thenReturn(countryDtoList);
        //When & Then
        mockMvc.perform(get("/v1/countries").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test Country")))
                .andExpect(jsonPath("$[0].nbpCurrencyRateDto.id", is(1)))
                .andExpect(jsonPath("$[0].nbpCurrencyRateDto.code", is("ZZZ")))
                .andExpect(jsonPath("$[0].nbpCurrencyRateDto.mid", is(0.01234)))
                .andExpect(jsonPath("$[0].nbpCurrencyRateDto.currencyName", is("Test currency")));

    }

    @Test
    public void shouldFetchCountry() throws Exception {
        //Given
        Mockito.when(countryController.getCountry(1L)).thenReturn(countryDto);
        //When & Then
        mockMvc.perform(get("/v1/countries/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Country")))
                .andExpect(jsonPath("$.nbpCurrencyRateDto.id", is(1)))
                .andExpect(jsonPath("$.nbpCurrencyRateDto.code", is("ZZZ")))
                .andExpect(jsonPath("$.nbpCurrencyRateDto.mid", is(0.01234)))
                .andExpect(jsonPath("$.nbpCurrencyRateDto.currencyName", is("Test currency")));
    }
}