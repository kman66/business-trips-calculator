package com.business.trips.calculator.domain.currencies;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class NbpConfig {

    @Value("${nbp.api.endpoint.prod}")
    private String nbpApiEndpoint;

    @Value("${nbp.api.format.json}")
    private String nbpFormatJson;

    @Value("${nbp.api.format.xml}")
    private String nbpFormatXml;
}
