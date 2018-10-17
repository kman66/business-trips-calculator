package com.business.trips.calculator.domain.parameters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParameterRepositoryTestSuite {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterRepositoryTestSuite.class);

    @Autowired
    private ParameterRepository parameterRepository;

    private List<Parameter> createParameters() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("parameter1", "value1"));
        parameters.add(new Parameter("parameter2", "value2"));
        parameters.add(new Parameter("parameter3", "value3"));
        return parameters;
    }

    @Test
    public void shouldSaveParameters() {
        //Given
        Parameter parameter1 = createParameters().get(0);
        //When
        parameterRepository.save(parameter1);
        //Then
        Long parameterId = parameter1.getId();
        String parameterName = parameter1.getName();
        String parameterValue = parameter1.getValue();
        Parameter fetchedParameter = parameterRepository.findOne(parameterId);
        Assert.assertNotNull(fetchedParameter);
        Assert.assertEquals(parameterId, fetchedParameter.getId());
        Assert.assertEquals(parameterName, fetchedParameter.getName());
        Assert.assertEquals(parameterValue, fetchedParameter.getValue());
        //Clean up
        try {
            parameterRepository.delete(parameterId);
        } catch (Exception e) {
            LOGGER.error("Error during deleting parameter.", e);
        }
    }

    @Test
    public void shouldFindAll() {
        //Given
        List<Parameter> parameters = createParameters();
        Long noOfRecordsBefore = parameterRepository.count();
        parameterRepository.save(parameters);
        Parameter parameter1 = parameters.get(0);
        Parameter parameter2 = parameters.get(1);
        Parameter parameter3 = parameters.get(2);
        //When
        List<Parameter> fetchedParameters = parameterRepository.findAll();
        //Then
        Assert.assertEquals(noOfRecordsBefore+3, fetchedParameters.size());
        Assert.assertEquals(parameter1.getId(), fetchedParameters.get(fetchedParameters.size()-3).getId());
        Assert.assertEquals(parameter1.getName(), fetchedParameters.get(fetchedParameters.size()-3).getName());
        Assert.assertEquals(parameter1.getValue(), fetchedParameters.get(fetchedParameters.size()-3).getValue());
        Assert.assertEquals(parameter2.getId(), fetchedParameters.get(fetchedParameters.size()-2).getId());
        Assert.assertEquals(parameter2.getName(), fetchedParameters.get(fetchedParameters.size()-2).getName());
        Assert.assertEquals(parameter2.getValue(), fetchedParameters.get(fetchedParameters.size()-2).getValue());
        Assert.assertEquals(parameter3.getId(), fetchedParameters.get(fetchedParameters.size()-1).getId());
        Assert.assertEquals(parameter3.getName(), fetchedParameters.get(fetchedParameters.size()-1).getName());
        Assert.assertEquals(parameter3.getValue(), fetchedParameters.get(fetchedParameters.size()-1).getValue());
        //Clean up
        try {
            parameterRepository.delete(parameters);
        } catch (Exception e) {
            LOGGER.error("Error during deleting parameters.", e);
        }
    }

    @Test
    public void shouldFindById() {
        //Given
        List<Parameter> parameters = createParameters();
        parameterRepository.save(parameters);
        Parameter parameter2 = parameters.get(1);
        //When
        Optional<Parameter> fetchedParameter = parameterRepository.findById(parameter2.getId());
        //Then
        Assert.assertEquals(parameter2.getId(), fetchedParameter.get().getId());
        Assert.assertEquals(parameter2.getName(), fetchedParameter.get().getName());
        Assert.assertEquals(parameter2.getValue(), fetchedParameter.get().getValue());
        //Clean up
        try {
            parameterRepository.delete(parameters);
        } catch (Exception e) {
            LOGGER.error("Error during deleting parameter.", e);
        }
    }
}