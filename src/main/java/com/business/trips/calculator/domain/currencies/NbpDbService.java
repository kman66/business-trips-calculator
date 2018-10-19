package com.business.trips.calculator.domain.currencies;

import com.business.trips.calculator.domain.parameters.Parameter;
import com.business.trips.calculator.domain.parameters.ParameterDto;
import com.business.trips.calculator.domain.parameters.ParameterMapper;
import com.business.trips.calculator.domain.parameters.ParameterRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NbpDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpDbService.class);

    private static final String FETCHED_CURRENCIES_EFFECTIVE_DATE = "fetched_currencies_effective_date";

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

    private List<NbpCurrencyRateDto> fetchCurrenciesFromTableA(final List<NbpTableADto> nbpTableADtoList) {
        return nbpTableADtoList.stream()
                .map(t -> t.getCurrencyRateDtos())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Access modifier was changed from private to default to enhance
     * testability
     */
    Parameter checkIfEffectiveDateInDbAndReturn(final List<NbpTableADto> nbpTableADtoList) {
        Optional<Parameter> fetchedCurrenciesEffectiveDate = parameterRepository.findByName(FETCHED_CURRENCIES_EFFECTIVE_DATE);
        if (fetchedCurrenciesEffectiveDate.isPresent()) {
            return parameterMapper.getMapperFacade().mapReverse(new ParameterDto(
                    fetchedCurrenciesEffectiveDate.get().getId(),
                    FETCHED_CURRENCIES_EFFECTIVE_DATE,
                    nbpTableADtoList.get(0).getEffectiveDate()
            ));
        }
        return parameterMapper.getMapperFacade().mapReverse(new ParameterDto(
                FETCHED_CURRENCIES_EFFECTIVE_DATE,
                nbpTableADtoList.get(0).getEffectiveDate()
        ));
    }

    private void saveEffectiveDate(final List<NbpTableADto> nbpTableADtoList) {
        parameterRepository.save(checkIfEffectiveDateInDbAndReturn(nbpTableADtoList));
    }

    private NbpCurrencyRate checkIfNbpCurrencyRateInDbExistsAndReturn(final NbpCurrencyRateDto nbpCurrencyRateDto) {
        Optional<NbpCurrencyRate> fetchedNbpCurrencyRate = nbpRepository.findByCode(nbpCurrencyRateDto.getCode());
        if (fetchedNbpCurrencyRate.isPresent()) {
            return fetchedNbpCurrencyRate.get();
        }
        return nbpCurrencyRateMapper.getMapperFacade().mapReverse(nbpCurrencyRateDto);
    }

    private void saveCurrencyRates(final List<NbpTableADto> nbpTableADtoList) {
        List<NbpCurrencyRateDto> nbpCurrencyRateDtoList = fetchCurrenciesFromTableA(nbpTableADtoList);
        nbpCurrencyRateDtoList.stream()
                .forEach(c -> nbpRepository.save(checkIfNbpCurrencyRateInDbExistsAndReturn(c)));
    }

    @Transactional
    public void saveNbpCurrencyRatesAndEffectiveDate() {
        List<NbpTableADto> nbpTableADtoList = nbpClient.getNbpTableA();
        LOGGER.info("Saving NBP currency rates..");
        saveCurrencyRates(nbpTableADtoList);
        saveEffectiveDate(nbpTableADtoList);
        LOGGER.info("..NBP currency rates have been successfully saved.");
    }

    public List<NbpCurrencyRate> fetchNbpCurrencyRates() {
        return nbpRepository.findAll();
    }
}
