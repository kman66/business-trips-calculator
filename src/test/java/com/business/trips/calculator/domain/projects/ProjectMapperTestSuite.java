package com.business.trips.calculator.domain.projects;

import com.business.trips.calculator.domain.countries.Country;
import com.business.trips.calculator.domain.countries.CountryDto;
import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import com.business.trips.calculator.domain.currencies.NbpCurrencyRateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectMapperTestSuite {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void shouldMapToProject() {
        //Given
        CountryDto countryDto = new CountryDto(1L, "Test country", new NbpCurrencyRateDto());
        ProjectDto projectDto = new ProjectDto(1L, "Test project", countryDto);
        //When
        Project mappedProject = projectMapper.maptToProject(projectDto);
        //Then
        assertNotNull(mappedProject);
        assertTrue(mappedProject instanceof Project);
        assertEquals(1L, mappedProject.getId(), 0);
        assertEquals("Test project", mappedProject.getName());
        assertNotNull(mappedProject.getCountry());
        assertTrue(mappedProject.getCountry() instanceof Country);
        assertEquals(1L, mappedProject.getCountry().getId(), 0);
        assertEquals("Test country", mappedProject.getCountry().getName());
        assertNotNull(mappedProject.getCountry().getNbpCurrencyRate());
        assertTrue(mappedProject.getCountry().getNbpCurrencyRate() instanceof NbpCurrencyRate);
    }

    @Test
    public void shouldMapToProjectDto() {
        //Given
        Country country = new Country(1L, "Test country", new NbpCurrencyRate());
        Project project = new Project(1L, "Test project", country);
        //When
        ProjectDto mappedProjectDto = projectMapper.maptToProjectDto(project);
        //Then
        assertNotNull(mappedProjectDto);
        assertTrue(mappedProjectDto instanceof ProjectDto);
        assertEquals(1L, mappedProjectDto.getId(), 0);
        assertEquals("Test project", mappedProjectDto.getName());
        assertNotNull(mappedProjectDto.getCountryDto());
        assertTrue(mappedProjectDto.getCountryDto() instanceof CountryDto);
        assertEquals(1L, mappedProjectDto.getCountryDto().getId(), 0);
        assertEquals("Test country", mappedProjectDto.getCountryDto().getName());
        assertNotNull(mappedProjectDto.getCountryDto().getNbpCurrencyRateDto());
        assertTrue(mappedProjectDto.getCountryDto().getNbpCurrencyRateDto() instanceof NbpCurrencyRateDto);
    }

    @Test
    public void shouldMapToProjectDtoList() {
        //Given
        Country country = new Country(1L, "Test country", new NbpCurrencyRate());
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project(1L, "Test project 1", country));
        projectList.add(new Project(120L, "Test project 120", country));
        //When
        List<ProjectDto> mappedProjectDtoList = projectMapper.mapToProjectDtoList(projectList);
        //Then
        assertEquals(2, mappedProjectDtoList.size());
        assertTrue(mappedProjectDtoList.get(0) instanceof ProjectDto);
        assertEquals(1L, mappedProjectDtoList.get(0).getId(), 0);
        assertEquals("Test project 1", mappedProjectDtoList.get(0).getName());
        assertNotNull(mappedProjectDtoList.get(0).getCountryDto());
        assertTrue(mappedProjectDtoList.get(0).getCountryDto() instanceof CountryDto);
        assertEquals(1L, mappedProjectDtoList.get(0).getCountryDto().getId(), 0);
        assertEquals("Test country", mappedProjectDtoList.get(0).getCountryDto().getName());
        assertNotNull(mappedProjectDtoList.get(0).getCountryDto().getNbpCurrencyRateDto());
        assertTrue(mappedProjectDtoList.get(0).getCountryDto().getNbpCurrencyRateDto() instanceof NbpCurrencyRateDto);
        assertTrue(mappedProjectDtoList.get(1) instanceof ProjectDto);
        assertEquals(120L, mappedProjectDtoList.get(1).getId(), 0);
        assertEquals("Test project 120", mappedProjectDtoList.get(1).getName());
        assertNotNull(mappedProjectDtoList.get(1).getCountryDto());
        assertTrue(mappedProjectDtoList.get(1).getCountryDto() instanceof CountryDto);
        assertEquals(1L, mappedProjectDtoList.get(1).getCountryDto().getId(), 0);
        assertEquals("Test country", mappedProjectDtoList.get(1).getCountryDto().getName());
        assertNotNull(mappedProjectDtoList.get(1).getCountryDto().getNbpCurrencyRateDto());
        assertTrue(mappedProjectDtoList.get(1).getCountryDto().getNbpCurrencyRateDto() instanceof NbpCurrencyRateDto);
    }
}