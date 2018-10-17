package com.business.trips.calculator.domain.currencies;

import org.junit.Assert;
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
public class NbpRepositoryTestSuite {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpRepositoryTestSuite.class);

    @Autowired
    private NbpRepository nbpRepository;

    private List<NbpCurrencyRate> createNbpCurrencyRates() {
        List<NbpCurrencyRate> currencyRates = new ArrayList<>();
        currencyRates.add(new NbpCurrencyRate("AAA", BigDecimal.valueOf(1.22450).setScale(5)));
        currencyRates.add(new NbpCurrencyRate("BBB", BigDecimal.valueOf(20.20030).setScale(5)));
        currencyRates.add(new NbpCurrencyRate("CCC", BigDecimal.valueOf(156.20000).setScale(5)));
        return currencyRates;
    }

    @Test
    public void shouldSaveNbpCurrencyRate() {
        //Given
        NbpCurrencyRate nbpCurrencyRate = createNbpCurrencyRates().get(0);
        //Then
        nbpRepository.save(nbpCurrencyRate);
        //When
        Long id = nbpCurrencyRate.getId();
        String code = nbpCurrencyRate.getCode();
        BigDecimal mid = nbpCurrencyRate.getMid();
        NbpCurrencyRate fetchedNbpCurrencyRate = nbpRepository.findOne(id);
        Assert.assertNotNull(fetchedNbpCurrencyRate);
        Assert.assertEquals(id, fetchedNbpCurrencyRate.getId());
        Assert.assertEquals(code, fetchedNbpCurrencyRate.getCode());
        Assert.assertEquals(mid, fetchedNbpCurrencyRate.getMid());
        //Clean up
        try {
            nbpRepository.delete(nbpCurrencyRate.getId());
        } catch (Exception e) {
            LOGGER.error("Error during deleting currency.", e);
        }
    }

    @Test
    public void shouldFindAllNbpCurrencyRates() {
        //Given
        List<NbpCurrencyRate> nbpCurrencyRates = createNbpCurrencyRates();
        Long noOfRecordsBefore = nbpRepository.count();
        nbpRepository.save(nbpCurrencyRates);
        NbpCurrencyRate nbpCurrencyRateAaa = nbpCurrencyRates.get(0);
        NbpCurrencyRate nbpCurrencyRateBbb = nbpCurrencyRates.get(1);
        NbpCurrencyRate nbpCurrencyRateCcc = nbpCurrencyRates.get(2);
        //When
        List<NbpCurrencyRate> fetchedNbpCurrencyRates = nbpRepository.findAll();
        //Then
        Assert.assertEquals(noOfRecordsBefore+3, fetchedNbpCurrencyRates.size());
        Assert.assertEquals(nbpCurrencyRateAaa.getId(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-3).getId());
        Assert.assertEquals(nbpCurrencyRateAaa.getCode(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-3).getCode());
        Assert.assertEquals(nbpCurrencyRateAaa.getMid(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-3).getMid());
        Assert.assertEquals(nbpCurrencyRateBbb.getId(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-2).getId());
        Assert.assertEquals(nbpCurrencyRateBbb.getCode(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-2).getCode());
        Assert.assertEquals(nbpCurrencyRateBbb.getMid(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-2).getMid());
        Assert.assertEquals(nbpCurrencyRateCcc.getId(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-1).getId());
        Assert.assertEquals(nbpCurrencyRateCcc.getCode(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-1).getCode());
        Assert.assertEquals(nbpCurrencyRateCcc.getMid(), fetchedNbpCurrencyRates.get(fetchedNbpCurrencyRates.size()-1).getMid());
        //Clean up
        try {
            nbpRepository.delete(nbpCurrencyRates);
        } catch (Exception e) {
            LOGGER.error("Error during delering currencies.", e);
        }
    }

    @Test
    public void shouldFindById() {
        //Given
        List<NbpCurrencyRate> nbpCurrencyRates = createNbpCurrencyRates();
        nbpRepository.save(nbpCurrencyRates);
        NbpCurrencyRate nbpCurrencyRateCcc = nbpCurrencyRates.get(2);
        //When
        Optional<NbpCurrencyRate> fetchedNbpCurrencyRate = nbpRepository.findById(nbpCurrencyRateCcc.getId());
        //Then
        Assert.assertEquals(nbpCurrencyRateCcc.getId(), fetchedNbpCurrencyRate.get().getId());
        Assert.assertEquals(nbpCurrencyRateCcc.getCode(), fetchedNbpCurrencyRate.get().getCode());
        Assert.assertEquals(nbpCurrencyRateCcc.getMid(), fetchedNbpCurrencyRate.get().getMid());
        //Clean up
        try {
            nbpRepository.delete(nbpCurrencyRates);
        } catch (Exception e) {
            LOGGER.error("Error during deleting parameter.", e);
        }
    }
}