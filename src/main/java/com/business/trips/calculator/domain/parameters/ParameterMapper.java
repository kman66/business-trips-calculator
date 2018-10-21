package com.business.trips.calculator.domain.parameters;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParameterMapper {

    public ParameterDto mapToParameterDto(final Parameter parameter) {
        return new ParameterDto(
                parameter.getId(),
                parameter.getName(),
                parameter.getValue()
        );
    }

    public List<ParameterDto> mapToParameterDtoList(final List<Parameter> parameters) {
        return parameters.stream()
                .map(p -> getMapperFacade().map(p))
                .collect(Collectors.toList());
    }

    public BoundMapperFacade<Parameter, ParameterDto> getMapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        return mapperFactory.getMapperFacade(Parameter.class, ParameterDto.class);
    }
}
