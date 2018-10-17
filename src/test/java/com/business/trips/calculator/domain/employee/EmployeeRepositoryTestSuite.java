package com.business.trips.calculator.domain.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTestSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryTestSuite.class);

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
        //Clean up
        try {
            employeeRepository.delete(id);
        } catch(Exception e) {
            LOGGER.error("Error during deleting employee.", e);
        }
    }

    @Test
    public void shouldFindAllEmployees() {
        //Given
        List<Employee> employeeList = createEmployees();
        Long noOfRecordsBeforeSave = employeeRepository.count();
        employeeRepository.save(employeeList);
        Employee johnSmith = employeeList.get(0);
        Employee johnBean = employeeList.get(1);
        Employee agatheCrispy = employeeList.get(2);
        //When
        List<Employee> fetchedEmployeeList = employeeRepository.findAll();
        //Then
        assertEquals(noOfRecordsBeforeSave+3, fetchedEmployeeList.size());
        assertEquals(johnSmith.getId(), fetchedEmployeeList.get(fetchedEmployeeList.size()-3).getId());
        assertEquals(johnBean.getId(), fetchedEmployeeList.get(fetchedEmployeeList.size()-2).getId());
        assertEquals(agatheCrispy.getId(), fetchedEmployeeList.get(fetchedEmployeeList.size()-1).getId());
        assertEquals(johnSmith.getForename(), fetchedEmployeeList.get(fetchedEmployeeList.size()-3).getForename());
        assertEquals(johnBean.getForename(), fetchedEmployeeList.get(fetchedEmployeeList.size()-2).getForename());
        assertEquals(agatheCrispy.getForename(), fetchedEmployeeList.get(fetchedEmployeeList.size()-1).getForename());
        assertEquals(johnSmith.getSurname(), fetchedEmployeeList.get(fetchedEmployeeList.size()-3).getSurname());
        assertEquals(johnBean.getSurname(), fetchedEmployeeList.get(fetchedEmployeeList.size()-2).getSurname());
        assertEquals(agatheCrispy.getSurname(), fetchedEmployeeList.get(fetchedEmployeeList.size()-1).getSurname());
        //Clean up
        try {
            employeeRepository.delete(johnSmith.getId());
            employeeRepository.delete(johnBean.getId());
            employeeRepository.delete(agatheCrispy.getId());
        } catch (Exception e) {
            LOGGER.error("Error during deleting employees.", e);
        }
    }
}