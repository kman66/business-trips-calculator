package com.business.trips.calculator.domain.parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParameterController.class)
public class ParameterControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParameterController parameterController;

    @Test
    public void shouldFetchParameters() throws Exception {
        //Given
        List<ParameterDto> parameterDtoList = new ArrayList<>();
        parameterDtoList.add(new ParameterDto(1L, "Test parameter", "Test value"));
        Mockito.when(parameterController.getParameters()).thenReturn(parameterDtoList);
        //When & Then
        mockMvc.perform(get("/v1/parameters").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test parameter")))
                .andExpect(jsonPath("$[0].value", is("Test value")));
    }

    @Test
    public void shouldFetchParameter() throws Exception {
        //Given
        ParameterDto parameterDto = new ParameterDto(1L, "Test parameter", "Test value");
        Mockito.when(parameterController.getParameter("Test parameter")).thenReturn(parameterDto);
        //When & Then
        mockMvc.perform(get("/v1/parameters/Test parameter").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test parameter")))
                .andExpect(jsonPath("$.value", is("Test value")));
    }
}