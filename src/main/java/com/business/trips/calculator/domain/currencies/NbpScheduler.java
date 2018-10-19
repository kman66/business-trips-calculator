package com.business.trips.calculator.domain.currencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NbpScheduler {

    @Autowired
    private NbpDbService nbpDbService;

    @Scheduled(cron = "0 30 16 ? * MON-FRI")
    public void saveNbpCurrenciesAndEffectiveDate() {
        nbpDbService.saveNbpCurrencyRatesAndEffectiveDate();
    }
}
