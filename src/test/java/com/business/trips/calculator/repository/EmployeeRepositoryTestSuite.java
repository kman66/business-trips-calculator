package com.business.trips.calculator.repository;

import com.business.trips.calculator.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTestSuite {
    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> createEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("John", "Smith"));
        employeeList.add(new Employee("John", "Bean"));
        employeeList.add(new Employee("Agathe", "Crispy"));
        return employeeList;
    }

    @Test
    public void shouldSaveEmployee() {
        //Given
        Employee johnSmith = createEmployees().get(0);
        //When
        employeeRepository.save(johnSmith);
        //Then
        Long id = johnSmith.getId();
        String surname = johnSmith.getSurname();
        String forename = johnSmith.getForename();
        Employee fetchedEmployee = employeeRepository.findOne(id);
        assertEquals(id, fetchedEmployee.getId());
        assertEquals(surname, fetchedEmployee.getSurname());
        assertEquals(forename, fetchedEmployee.getForename());

        try {
            employeeRepository.delete(id);
        } catch(Exception e) {

        }
    }

    @Test
    public void shouldFindAllEmployees() {
        //Given
        List<Employee> employeeList = createEmployees();
        employeeRepository.save(employeeList);
        Employee johnSmith = employeeList.get(0);
        Employee johnBean = employeeList.get(1);
        Employee agatheCrispy = employeeList.get(2);
        //When
        List<Employee> fetchedEmployeeList = employeeRepository.findAll();
        //Then
        assertEquals(3, fetchedEmployeeList.size());
        assertEquals(johnSmith.getId(), fetchedEmployeeList.get(0).getId());
        assertEquals(johnBean.getId(), fetchedEmployeeList.get(1).getId());
        assertEquals(agatheCrispy.getId(), fetchedEmployeeList.get(2).getId());
        assertEquals(johnSmith.getForename(), fetchedEmployeeList.get(0).getForename());
        assertEquals(johnBean.getForename(), fetchedEmployeeList.get(1).getForename());
        assertEquals(agatheCrispy.getForename(), fetchedEmployeeList.get(2).getForename());
        assertEquals(johnSmith.getSurname(), fetchedEmployeeList.get(0).getSurname());
        assertEquals(johnBean.getSurname(), fetchedEmployeeList.get(1).getSurname());
        assertEquals(agatheCrispy.getSurname(), fetchedEmployeeList.get(2).getSurname());

        try {
            employeeRepository.delete(fetchedEmployeeList);
        } catch (Exception e) {

        }
    }
}