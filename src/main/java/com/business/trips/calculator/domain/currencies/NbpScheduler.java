package com.business.trips.calculator.domain.currencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NbpScheduler {

    @Autowired
    private NbpDbService nbpDbService;

    @Scheduled(fixedRate = 5000)
    public void saveNbpCurrenciesAndEffectiveDate() {
        nbpDbService.saveNbpCurrencyRatesAndEffectiveDate();
    }
}
