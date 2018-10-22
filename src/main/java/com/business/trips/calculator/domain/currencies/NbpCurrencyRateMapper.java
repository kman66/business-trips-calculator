package com.business.trips.calculator.domain.currencies;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NbpCurrencyRateMapper {

    public List<NbpCurrencyRateDto> mapToNbpCurrencyRateDtoList(final List<NbpCurrencyRate> nbpCurrencyRateList) {
        return nbpCurrencyRateList.stream()
                .map(n -> getMapperFacade().map(n))
                .collect(Collectors.toList());
    }

    public BoundMapperFacade<NbpCurrencyRate, NbpCurrencyRateDto> getMapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        return mapperFactory.getMapperFacade(NbpCurrencyRate.class, NbpCurrencyRateDto.class);
    }
}
