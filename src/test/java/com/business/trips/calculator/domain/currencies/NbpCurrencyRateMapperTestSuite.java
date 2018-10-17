package com.business.trips.calculator.domain.currencies;

import org.junit.Assert;
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
public class NbpCurrencyRateMapperTestSuite {

    @Autowired
    private NbpCurrencyRateMapper nbpCurrencyRateMapper;

    @Test
    public void shouldMapToNbpCurrencyRate() {
        //Given
        NbpCurrencyRateDto nbpCurrencyRateDto = new NbpCurrencyRateDto(1L, "TST", BigDecimal.valueOf(0.34200).setScale(5));
        //When
        NbpCurrencyRate mappedNbpCurrencyRate = nbpCurrencyRateMapper.mapToNbpCurrencyRate(nbpCurrencyRateDto);
        //Then
        Assert.assertNotNull(mappedNbpCurrencyRate);
        Assert.assertTrue(mappedNbpCurrencyRate instanceof NbpCurrencyRate);
        Assert.assertEquals(1L, mappedNbpCurrencyRate.getId(), 0);
        Assert.assertEquals("TST", mappedNbpCurrencyRate.getCode());
        Assert.assertEquals(BigDecimal.valueOf(0.34200).setScale(5), mappedNbpCurrencyRate.getMid());
    }

    @Test
    public void shouldMapToNbpCurrencyRateDto() {
        //Given
        NbpCurrencyRate nbpCurrencyRate = new NbpCurrencyRate(1L, "TST", BigDecimal.valueOf(0.34200).setScale(5));
        //When
        NbpCurrencyRateDto mappedNbpCurrencyRateDto = nbpCurrencyRateMapper.mapToNbpCurrencyRateDto(nbpCurrencyRate);
        //Then
        Assert.assertNotNull(mappedNbpCurrencyRateDto);
        Assert.assertTrue(mappedNbpCurrencyRateDto instanceof NbpCurrencyRateDto);
        Assert.assertEquals(1L, mappedNbpCurrencyRateDto.getId(), 0);
        Assert.assertEquals("TST", mappedNbpCurrencyRateDto.getCode());
        Assert.assertEquals(BigDecimal.valueOf(0.34200).setScale(5), mappedNbpCurrencyRateDto.getMid());
    }

    @Test
    public void shouldMapToNbpCurrencyRateDtoList() {
        //Given
        List<NbpCurrencyRate> nbpCurrencyRates = new ArrayList<>();
        nbpCurrencyRates.add(new NbpCurrencyRate(34L, "XXX", BigDecimal.valueOf(4.56211).setScale(5)));
        nbpCurrencyRates.add(new NbpCurrencyRate(37L, "ZZZ", BigDecimal.valueOf(1.45234).setScale(5)));
        //When
        List<NbpCurrencyRateDto> mappedNbpCurrencyDtoList = nbpCurrencyRateMapper.mapToNbpCurrencyRateDtoList(nbpCurrencyRates);
        //Then
        assertNotNull(mappedNbpCurrencyDtoList);
        assertEquals(2, mappedNbpCurrencyDtoList.size());
        assertTrue(mappedNbpCurrencyDtoList.get(0) instanceof NbpCurrencyRateDto);
        assertEquals(34L, mappedNbpCurrencyDtoList.get(0).getId(), 0);
        assertEquals("XXX", mappedNbpCurrencyDtoList.get(0).getCode());
        assertEquals(BigDecimal.valueOf(4.56211).setScale(5), mappedNbpCurrencyDtoList.get(0).getMid());
        assertTrue(mappedNbpCurrencyDtoList.get(1) instanceof NbpCurrencyRateDto);
        assertEquals(37L, mappedNbpCurrencyDtoList.get(1).getId(), 0);
        assertEquals("ZZZ", mappedNbpCurrencyDtoList.get(1).getCode());
        assertEquals(BigDecimal.valueOf(1.45234).setScale(5), mappedNbpCurrencyDtoList.get(1).getMid());
    }
}