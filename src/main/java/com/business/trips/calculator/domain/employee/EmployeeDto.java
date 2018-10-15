package com.business.trips.calculator.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeDto {
    private Long id;
    private String forename;
    private String surname;

    public EmployeeDto(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
