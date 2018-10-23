package com.business.trips.calculator.domain.projects;

import com.business.trips.calculator.domain.countries.Country;
import com.business.trips.calculator.domain.countries.CountryRepository;
import com.business.trips.calculator.domain.currencies.NbpCurrencyRate;
import com.business.trips.calculator.domain.currencies.NbpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTestSuite {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRepositoryTestSuite.class);

    private Country country;

    private NbpCurrencyRate nbpCurrencyRate;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private NbpRepository nbpRepository;

    private List<Project> createProjects() {
        nbpCurrencyRate = new NbpCurrencyRate("ZZZ", BigDecimal.valueOf(3.33211).setScale(5), "Test currency");
        country = new Country("Test country", nbpCurrencyRate);
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project("Test Project 1", country));
        projectList.add(new Project("Test Project 2", country));
        return projectList;
    }

    @Test
    public void shouldSaveProject() {
        //Given
        Project project = createProjects().get(0);
        nbpRepository.save(nbpCurrencyRate);
        countryRepository.save(country);
        //When
        projectRepository.save(project);
        //Then
        Project savedProject = projectRepository.findOne(project.getId());
        assertNotNull(savedProject);
        assertTrue(savedProject instanceof Project);
        assertEquals(project.getId(), savedProject.getId(), 0);
        assertEquals(project.getName(), savedProject.getName());
        assertNotNull(savedProject.getCountry());
        assertTrue(savedProject.getCountry() instanceof Country);
        assertEquals(project.getCountry().getId(), savedProject.getCountry().getId(), 0);
        assertEquals(project.getCountry().getName(), savedProject.getCountry().getName());
        assertNotNull(project.getCountry().getNbpCurrencyRate());
        //Clean up
        try {
            projectRepository.delete(project.getId());
            countryRepository.delete(country.getId());
            nbpRepository.delete(nbpCurrencyRate.getId());
        } catch(Exception e) {
            LOGGER.error("Error during deleting project.", e);
        }
    }

    @Test
    public void shouldFindById() {
        //Given
        List<Project> projectList = createProjects();
        nbpRepository.save(nbpCurrencyRate);
        countryRepository.save(country);
        projectRepository.save(projectList);
        Project project = projectList.get(1);
        //When
        Optional<Project> optionalProject = projectRepository.findById(project.getId());
        //Then
        assertNotNull(optionalProject.get());
        assertTrue(optionalProject.get() instanceof Project);
        assertEquals(project.getId(), optionalProject.get().getId(), 0);
        assertEquals(project.getName(), optionalProject.get().getName());
        assertNotNull(optionalProject.get().getCountry());
        assertTrue(optionalProject.get().getCountry() instanceof Country);
        //Clean up
        try {
            projectRepository.delete(projectList);
            countryRepository.delete(country.getId());
            nbpRepository.delete(nbpCurrencyRate.getId());
        } catch(Exception e) {
            LOGGER.error("Error during deleting projects.", e);
        }
    }

    @Test
    public void shouldFindByName() {
        //Given
        List<Project> projectList = createProjects();
        nbpRepository.save(nbpCurrencyRate);
        countryRepository.save(country);
        projectRepository.save(projectList);
        Project project = projectList.get(1);
        //When
        Optional<Project> optionalProject = projectRepository.findByName(project.getName());
        //Then
        assertNotNull(optionalProject.get());
        assertTrue(optionalProject.get() instanceof Project);
        assertEquals(project.getId(), optionalProject.get().getId(), 0);
        assertEquals(project.getName(), optionalProject.get().getName());
        assertNotNull(optionalProject.get().getCountry());
        assertTrue(optionalProject.get().getCountry() instanceof Country);
        //Clean up
        try {
            projectRepository.delete(projectList);
            countryRepository.delete(country.getId());
            nbpRepository.delete(nbpCurrencyRate.getId());
        } catch(Exception e) {
            LOGGER.error("Error during deleting projects.", e);
        }
    }
}