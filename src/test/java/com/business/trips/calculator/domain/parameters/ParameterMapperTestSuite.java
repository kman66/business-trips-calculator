package com.business.trips.calculator.domain.parameters;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParameterMapperTestSuite {

    @Autowired
    private ParameterMapper parameterMapper;

    @Test
    public void shouldMapToParameterDto() {
        //Given
        Parameter parameter = new Parameter(1L, "Test parameter", "Test value");
        //When
        ParameterDto mappedParameterDto = parameterMapper.mapToParameterDto(parameter);
        //Then
        Assert.assertNotNull(mappedParameterDto);
        Assert.assertTrue(mappedParameterDto instanceof ParameterDto);
        Assert.assertEquals(1L, mappedParameterDto.getId(), 0);
        Assert.assertEquals("Test parameter", mappedParameterDto.getName());
        Assert.assertEquals("Test value", mappedParameterDto.getValue());
    }

    @Test
    public void shouldMapToParameterDtoList() {
        //Given
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter(1L, "Parameter1", "Value1"));
        parameters.add(new Parameter(23L, "Parameter23", "Value23"));
        parameters.add(new Parameter(76L, "Parameter76", "Value76"));
        //When
        List<ParameterDto> mappedParameterDtoList = parameterMapper.mapToParameterDtoList(parameters);
        //Then
        Assert.assertNotNull(mappedParameterDtoList);
        Assert.assertEquals(3, mappedParameterDtoList.size());
        Assert.assertTrue(mappedParameterDtoList.get(0) instanceof ParameterDto);
        Assert.assertEquals(1L, mappedParameterDtoList.get(0).getId(), 0);
        Assert.assertEquals("Parameter1", mappedParameterDtoList.get(0).getName());
        Assert.assertEquals("Value1", mappedParameterDtoList.get(0).getValue());
        Assert.assertTrue(mappedParameterDtoList.get(1) instanceof ParameterDto);
        Assert.assertEquals(23L, mappedParameterDtoList.get(1).getId(), 0);
        Assert.assertEquals("Parameter23", mappedParameterDtoList.get(1).getName());
        Assert.assertEquals("Value23", mappedParameterDtoList.get(1).getValue());
        Assert.assertTrue(mappedParameterDtoList.get(2) instanceof ParameterDto);
        Assert.assertEquals(76L, mappedParameterDtoList.get(2).getId(), 0);
        Assert.assertEquals("Parameter76", mappedParameterDtoList.get(2).getName());
        Assert.assertEquals("Value76", mappedParameterDtoList.get(2).getValue());
    }
}