package com.business.trips.calculator.domain.countries;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    @Override
    List<Country> findAll();

    @Override
    Country save(Country country);

    Optional<Country> findById(Long countryId);

    Optional<Country> findByName(String countryName);
}
