package com.business.trips.calculator.domain.currencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NbpCurrencyService {

    @Autowired
    private NbpClient nbpClient;

    public List<NbpCurrencyRateDto> fetchCurrencies() {
        return nbpClient.getNbpTableA().stream()
                .map(t -> t.getCurrencyRateDtos())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
