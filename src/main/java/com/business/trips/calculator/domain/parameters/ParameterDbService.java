package com.business.trips.calculator.domain.parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterDbService.class);

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private ParameterMapper parameterMapper;

    public ParameterDto extractParameterFromDbOrThrowException(final String parameterName) throws ParameterNotFoundException {
        LOGGER.info("Extracting parameter ’" + parameterName + "'...");
        ParameterDto extractedParameterDto = parameterMapper.mapToParameterDto(parameterRepository.findByName(parameterName)
                .orElseThrow(() -> new ParameterNotFoundException("Parameter '" + parameterName + "' has not been found.")));
        LOGGER.info("...extracted parameter: " + extractedParameterDto);
        return extractedParameterDto;
    }
}
