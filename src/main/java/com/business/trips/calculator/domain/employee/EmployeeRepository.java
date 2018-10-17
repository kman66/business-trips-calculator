package com.business.trips.calculator.domain.employee;

import com.business.trips.calculator.domain.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    @Override
    Employee save(Employee employee);

    Optional<Employee> findById(Long employeeId);
}
