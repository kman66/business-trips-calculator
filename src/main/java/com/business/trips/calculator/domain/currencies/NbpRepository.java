package com.business.trips.calculator.domain.currencies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NbpRepository extends CrudRepository<NbpCurrencyRate, Long> {

    @Override
    List<NbpCurrencyRate> findAll();

    @Override
    NbpCurrencyRate save(NbpCurrencyRate nbpCurrencyRate);

    Optional<NbpCurrencyRate> findById(Long currencyId);

}
