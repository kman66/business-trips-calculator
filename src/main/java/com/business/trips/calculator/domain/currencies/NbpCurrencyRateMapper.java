package com.business.trips.calculator.domain.currencies;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NbpCurrencyRateMapper {
    public NbpCurrencyRate mapToNbpCurrencyRate(final NbpCurrencyRateDto nbpCurrencyRateDto) {
        return new NbpCurrencyRate(
                nbpCurrencyRateDto.getId(),
                nbpCurrencyRateDto.getCode(),
                nbpCurrencyRateDto.getMid()
        );
    }

    public NbpCurrencyRateDto mapToNbpCurrencyRateDto(final NbpCurrencyRate nbpCurrencyRate) {
        return new NbpCurrencyRateDto(
                nbpCurrencyRate.getId(),
                nbpCurrencyRate.getCode(),
                nbpCurrencyRate.getMid()
        );
    }

    public List<NbpCurrencyRateDto> mapToNbpCurrencyRateDtoList(final List<NbpCurrencyRate> nbpCurrencyRateList) {
        return nbpCurrencyRateList.stream()
                .map(n -> new NbpCurrencyRateDto(n.getId(), n.getCode(), n.getMid()))
                .collect(Collectors.toList());
    }
}
