package com.business.trips.calculator.domain.parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ParameterDbServiceTestSuite {

    @InjectMocks
    private ParameterDbService parameterDbService;

    @Mock
    private ParameterRepository parameterRepository;

    @Mock
    private ParameterMapper parameterMapper;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionByExtractingParameter() {
        //Given
        thrown.expect(ParameterNotFoundException.class);
        Mockito.when(parameterRepository.findByName("test")).thenThrow(new ParameterNotFoundException());
        //When
        parameterDbService.extractParameterFromDbOrThrowException("test");
        //Then
        fail("This method should throw the ParameterNotFoundException");
    }

    @Test
    public void shouldExtractParameterFromDb() {
        //Given
        ParameterDto parameterDto = new ParameterDto(1L, "Test parameter", "Test value");
        Parameter parameter = new Parameter(1L, "Test parameter", "Test value");
        Optional<Parameter> optionalParameter = Optional.of(parameter);
        Mockito.when(parameterRepository.findByName("Test parameter")).thenReturn(optionalParameter);
        Mockito.when(parameterMapper.mapToParameterDto(optionalParameter.get())).thenReturn(parameterDto);
        //When
        ParameterDto extractedParameterDto = parameterDbService.extractParameterFromDbOrThrowException("Test parameter");
        //Then
        assertNotNull(extractedParameterDto);
        assertTrue(extractedParameterDto instanceof ParameterDto);
        assertEquals(1L, extractedParameterDto.getId(), 0);
        assertEquals("Test parameter", extractedParameterDto.getName());
        assertEquals("Test value", extractedParameterDto.getValue());
    }
}