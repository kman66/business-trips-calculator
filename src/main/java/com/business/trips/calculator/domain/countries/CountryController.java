package com.business.trips.calculator.domain.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryDbService countryDbService;

    @GetMapping(value = "/countries")
    public List<CountryDto> getCountries() {
        return countryMapper.mapToCountryDtoList(countryRepository.findAll());
    }

    @GetMapping(value = "/countries/{countryId}")
    public CountryDto getCountry(@PathVariable Long countryId) throws CountryNotFoundException {
        return countryDbService.extractCountryFromDbOrThrowException(countryId);
    }
}
