package com.business.trips.calculator.domain.projects;

import com.business.trips.calculator.domain.countries.Country;
import com.business.trips.calculator.domain.countries.CountryDto;
import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import com.business.trips.calculator.domain.currencies.NbpCurrencyRateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectController projectController;

    @Test
    public void shouldFetchCountries() throws Exception {
        //Given
        List<ProjectDto> projectDtoList = new ArrayList<>();
        CountryDto countryDto = new CountryDto(1L, "Test country", new NbpCurrencyRateDto());
        projectDtoList.add(new ProjectDto(1L, "Test project 1", countryDto));
        Mockito.when(projectController.getProjects()).thenReturn(projectDtoList);
        //When & Then
        mockMvc.perform(get("/v1/projects").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test project 1")))
                .andExpect(jsonPath("$[0].country.id", is(1)))
                .andExpect(jsonPath("$[0].country.name", is("Test country")));
    }
}