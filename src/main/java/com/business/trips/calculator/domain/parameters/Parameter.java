package com.business.trips.calculator.domain.parameters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "PARAMETERS")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "parameters_seq")
    @SequenceGenerator(name = "parameters_seq", sequenceName = "public.parameters_id_seq")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
