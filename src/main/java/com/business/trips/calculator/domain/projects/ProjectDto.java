package com.business.trips.calculator.domain.projects;

import com.business.trips.calculator.domain.countries.CountryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectDto {
    private Long id;
    private String name;
    @JsonProperty(value = "country")
    private CountryDto countryDto;
}
