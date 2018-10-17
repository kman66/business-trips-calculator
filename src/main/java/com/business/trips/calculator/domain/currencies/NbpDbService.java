package com.business.trips.calculator.domain.currencies;

import com.business.trips.calculator.domain.parameters.ParameterDto;
import com.business.trips.calculator.domain.parameters.ParameterMapper;
import com.business.trips.calculator.domain.parameters.ParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NbpDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpDbService.class);

    @Autowired
    private NbpRepository nbpRepository;

    @Autowired
    private NbpCurrencyRateMapper nbpCurrencyRateMapper;

    @Autowired
    private NbpClient nbpClient;

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private ParameterMapper parameterMapper;

    private List<NbpCurrencyRateDto> fetchCurrenciesFromTableA(List<NbpTableADto> nbpTableADtoList) {
        return nbpTableADtoList.stream()
                .map(t -> t.getCurrencyRateDtos())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void saveNbpCurrencyRatesAndEffectiveDate() {
        List<NbpTableADto> nbpTableADtoList = nbpClient.getNbpTableA();
        List<NbpCurrencyRateDto> nbpCurrencyRateDtoList = fetchCurrenciesFromTableA(nbpTableADtoList);
        LOGGER.info("Saving NBP currency rates..");
        parameterRepository.save(
                parameterMapper.mapToParameter(new ParameterDto(
                        "fetched_currencies_effective_date",
                        nbpTableADtoList.get(0).getEffectiveDate()))
        );
        nbpCurrencyRateDtoList.stream()
                .forEach(c -> nbpRepository.save(
                        nbpCurrencyRateMapper.mapToNbpCurrencyRate(c)
                ));
        LOGGER.info("..NBP currency rates have been successfully saved.");
    }

    public List<NbpCurrencyRate> fetchNbpCurrencyRates() {
        return nbpRepository.findAll();
    }
}
