package com.business.trips.calculator.domain.parameters;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.tags.Param;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParameterMapper {
    public Parameter mapToParameter(final ParameterDto parameterDto) {
        return new Parameter(
                parameterDto.getId(),
                parameterDto.getName(),
                parameterDto.getValue()
        );
    }

    public ParameterDto mapToParameterDto(final Parameter parameter) {
        return new ParameterDto(
                parameter.getId(),
                parameter.getName(),
                parameter.getValue()
        );
    }

    public List<ParameterDto> mapToParameterDtoList(final List<Parameter> parameters) {
        return parameters.stream()
                .map(p -> new ParameterDto(p.getId(), p.getName(), p.getValue()))
                .collect(Collectors.toList());
    }
}
