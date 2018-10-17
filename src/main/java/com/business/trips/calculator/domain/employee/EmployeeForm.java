package com.business.trips.calculator.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeForm {
    private Long id;
    private String forename;
    private String surname;
}
