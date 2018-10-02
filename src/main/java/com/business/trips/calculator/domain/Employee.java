package com.business.trips.calculator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FORENAME")
    private String forename;

    @Column(name = "SURNAME")
    private String surname;

    public Employee(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }
}
