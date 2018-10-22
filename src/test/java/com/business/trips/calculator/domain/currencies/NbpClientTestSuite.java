package com.business.trips.calculator.domain.currencies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NbpClientTestSuite {

    @InjectMocks
    private NbpClient nbpClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private NbpConfig nbpConfig;

    @Before
    public void init() {
        Mockito.when(nbpConfig.getNbpApiEndpoint()).thenReturn("http://test.com");
        Mockito.when(nbpConfig.getNbpFormatJson()).thenReturn("test");
    }

    @Test
    public void shouldFetchEmptyNbpTableA() throws URISyntaxException {
        //Given
        NbpTableADto[] nbpTableADtos = new NbpTableADto[0];
        URI uri = new URI("http://test.com/tables/A?format=test");
        Mockito.when(restTemplate.getForObject(uri, NbpTableADto[].class)).thenReturn(nbpTableADtos);
        //When
        List<NbpTableADto> fetchedNbpTableA = nbpClient.getNbpTableA();
        //Then
        Assert.assertEquals(0, fetchedNbpTableA.size());
    }

    @Test
    public void shouldFetchNbpTableA() throws URISyntaxException {
        //Given
        List<NbpCurrencyRateDto> currencyRateDtos = new ArrayList<>();
        currencyRateDtos.add(new NbpCurrencyRateDto(1L, "TST1", BigDecimal.valueOf(1.5), "Test name 1"));
        currencyRateDtos.add(new NbpCurrencyRateDto(2L, "TST2", BigDecimal.valueOf(2.5), "Test name 2"));
        NbpTableADto[] nbpTableADtos = new NbpTableADto[1];
        nbpTableADtos[0] = new NbpTableADto("A", "1", "2000-10-10", currencyRateDtos);
        URI uri = new URI("http://test.com/tables/A?format=test");
        Mockito.when(restTemplate.getForObject(uri, NbpTableADto[].class)).thenReturn(nbpTableADtos);
        //When
        List<NbpTableADto> fetchedNbpTableA = nbpClient.getNbpTableA();
        //Then
        Assert.assertEquals(1, fetchedNbpTableA.size());
        Assert.assertEquals("A", fetchedNbpTableA.get(0).getTable());
        Assert.assertEquals("1", fetchedNbpTableA.get(0).getNo());
        Assert.assertEquals("2000-10-10", fetchedNbpTableA.get(0).getEffectiveDate());
        Assert.assertEquals(2, fetchedNbpTableA.get(0).getCurrencyRateDtos().size());
        Assert.assertEquals(1L, fetchedNbpTableA.get(0).getCurrencyRateDtos().get(0).getId(), 0);
        Assert.assertEquals("TST1", fetchedNbpTableA.get(0).getCurrencyRateDtos().get(0).getCode());
        Assert.assertEquals(BigDecimal.valueOf(1.5), fetchedNbpTableA.get(0).getCurrencyRateDtos().get(0).getMid());
        Assert.assertEquals("Test name 1", fetchedNbpTableA.get(0).getCurrencyRateDtos().get(0).getCurrencyName());
        Assert.assertEquals(2L, fetchedNbpTableA.get(0).getCurrencyRateDtos().get(1).getId(), 0);
        Assert.assertEquals("TST2", fetchedNbpTableA.get(0).getCurrencyRateDtos().get(1).getCode());
        Assert.assertEquals(BigDecimal.valueOf(2.5), fetchedNbpTableA.get(0).getCurrencyRateDtos().get(1).getMid());
        Assert.assertEquals("Test name 2", fetchedNbpTableA.get(0).getCurrencyRateDtos().get(1).getCurrencyName());
    }
}