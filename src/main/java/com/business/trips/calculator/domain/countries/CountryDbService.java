package com.business.trips.calculator.domain.countries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDbService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    public CountryDto extractCountryFromDbOrThrowException(final Long countryId) throws CountryNotFoundException {
        LOGGER.info("Extracting country with id=" + countryId + "...");
        CountryDto extractedCountryDto = countryMapper.mapToCountryDto(countryRepository.findById(countryId)
        .orElseThrow(() -> new CountryNotFoundException("Country with id=" + countryId + " has not been found.")));
        LOGGER.info("...extracted country: " + extractedCountryDto);
        return extractedCountryDto;
    }
}
