package com.business.trips.calculator.domain.projects;

import com.business.trips.calculator.domain.countries.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    @Autowired
    private CountryMapper countryMapper;

    public Project maptToProject(final ProjectDto projectDto) {
        return new Project(
                projectDto.getId(),
                projectDto.getName(),
                countryMapper.mapToCountry(projectDto.getCountryDto())
        );
    }

    public ProjectDto maptToProjectDto(final Project project) {
        return new ProjectDto(
                project.getId(),
                project.getName(),
                countryMapper.mapToCountryDto(project.getCountry())
        );
    }

    public List<ProjectDto> mapToProjectDtoList(final List<Project> projectList) {
        return projectList.stream()
                .map(p -> maptToProjectDto(p))
                .collect(Collectors.toList());
    }
}
