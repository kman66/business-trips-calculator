package com.business.trips.calculator.domain.employee;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
                .map(t -> getMapperFacade().map(t))
                .collect(Collectors.toList());
    }

    public EmployeeForm mapFromEmployeeDtoToEmployeeForm(final EmployeeDto employeeDto) {
        return new EmployeeForm(
                employeeDto.getId(),
                employeeDto.getForename(),
                employeeDto.getSurname()
        );
    }

    public BoundMapperFacade<Employee, EmployeeDto> getMapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        return mapperFactory.getMapperFacade(Employee.class, EmployeeDto.class);
    }
}
