package com.business.trips.calculator.domain.projects;

import com.business.trips.calculator.domain.countries.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "PROJECTS")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "projects_seq")
    @SequenceGenerator(name = "projects_seq", sequenceName = "public.projects_id_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    public Project(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
