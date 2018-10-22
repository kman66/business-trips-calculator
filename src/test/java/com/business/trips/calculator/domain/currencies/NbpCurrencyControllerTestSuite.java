package com.business.trips.calculator.domain.currencies;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NbpCurrencyController.class)
public class NbpCurrencyControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NbpCurrencyController nbpCurrencyController;

    @Test
    public void shouldFetchEmptyCurrencies() throws Exception {
        //Given
        List<NbpCurrencyRateDto> currencyRateDtos = new ArrayList<>();
        Mockito.when(nbpCurrencyController.getNbpCurrencies()).thenReturn(currencyRateDtos);
        //When & Then
        mockMvc.perform(get("/v1/currencies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void shouldFetchCurrencies() throws Exception {
        //Given
        List<NbpCurrencyRateDto> currencyRateDtos = new ArrayList<>();
        currencyRateDtos.add(new NbpCurrencyRateDto(1L, "TST", BigDecimal.valueOf(12.3), "Test name"));
        Mockito.when(nbpCurrencyController.getNbpCurrencies()).thenReturn(currencyRateDtos);
        //When & Then
        mockMvc.perform(get("/v1/currencies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].code", Matchers.is("TST")))
                .andExpect(jsonPath("$[0].mid", Matchers.is(12.3)))
                .andExpect(jsonPath("$[0].currencyName", Matchers.is("Test name")));
    }
}