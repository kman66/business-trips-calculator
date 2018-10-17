package com.business.trips.calculator.domain.employee;

import com.business.trips.calculator.domain.employee.Employee;
import com.business.trips.calculator.domain.employee.EmployeeDto;
import com.business.trips.calculator.domain.employee.EmployeeForm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public Employee mapToEmployee(final EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getForename(),
                employeeDto.getSurname()
        );
    }

    public EmployeeDto mapToEmployeeDto(final Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getForename(),
                employee.getSurname()
        );
    }

    public List<EmployeeDto> mapToEmployeeDtoList(final List<Employee> employeeList) {
        return employeeList.stream()
                .map(t -> new EmployeeDto(t.getId(), t.getForename(), t.getSurname()))
                .collect(Collectors.toList());
    }

    public EmployeeForm mapFromEmployeeDtoToEmployeeForm(final EmployeeDto employeeDto) {
        return new EmployeeForm(
                employeeDto.getId(),
                employeeDto.getForename(),
                employeeDto.getSurname()
        );
    }
}
