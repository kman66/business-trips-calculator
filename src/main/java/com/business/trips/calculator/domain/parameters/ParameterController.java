package com.business.trips.calculator.domain.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class ParameterController {

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private ParameterMapper parameterMapper;

    @Autowired
    private ParameterDbService parameterDbService;

    @GetMapping(value = "/parameters")
    public List<ParameterDto> getParameters() {
        return parameterMapper.mapToParameterDtoList(parameterRepository.findAll());
    }

    @GetMapping(value = "/parameters/{parameterName}")
    public ParameterDto getParameter(@PathVariable String parameterName) throws ParameterNotFoundException {
        return parameterDbService.extractParameterFromDbOrThrowException(parameterName);
    }
}
