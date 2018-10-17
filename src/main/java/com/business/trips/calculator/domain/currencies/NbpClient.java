package com.business.trips.calculator.domain.currencies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NbpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NbpConfig nbpConfig;

    public List<NbpTableADto> getNbpTableA() {
        try {
            NbpTableADto[] nbpTableResponse = restTemplate.getForObject(buildUrlForNbpTable(), NbpTableADto[].class);
            return Arrays.asList(Optional.of(nbpTableResponse).orElse(new NbpTableADto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI buildUrlForNbpTable() {
        return UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + "/tables/A")
                .queryParam("format", nbpConfig.getNbpFormatJson())
                .build().encode().toUri();
    }
}
