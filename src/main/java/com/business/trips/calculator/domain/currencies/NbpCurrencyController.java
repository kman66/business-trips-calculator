package com.business.trips.calculator.domain.currencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class NbpCurrencyController {

    @Autowired
    private NbpDbService nbpDbService;

    @Autowired
    private NbpCurrencyRateMapper nbpCurrencyRateMapper;

    @GetMapping(value = "/currencies")
    public List<NbpCurrencyRateDto> getNbpCurrencies() {
        return nbpCurrencyRateMapper.mapToNbpCurrencyRateDtoList(nbpDbService.fetchNbpCurrencyRates());
    }
}
