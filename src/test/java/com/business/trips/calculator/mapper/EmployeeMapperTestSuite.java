package com.business.trips.calculator.mapper;

import com.business.trips.calculator.domain.Employee;
import com.business.trips.calculator.domain.EmployeeDto;
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
public class EmployeeMapperTestSuite {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void shouldMapToEmployeeDto() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(1L, "John", "Smith");
        //When
        Employee employee = employeeMapper.mapToEmployee(employeeDto);
        //Then
        assertNotNull(employee);
        assertTrue(employee instanceof Employee);
        assertEquals(1L, employee.getId(),0);
        assertEquals("John", employee.getForename());
        assertEquals("Smith", employee.getSurname());
    }

    @Test
    public void shouldMapToEmployee() {
        //Given
        Employee employee = new Employee(1L, "John", "Smith");
        //When
        EmployeeDto employeeDto = employeeMapper.mapToEmployeeDto(employee);
        //Then
        assertNotNull(employeeDto);
        assertTrue(employeeDto instanceof EmployeeDto);
        assertEquals(1L, employeeDto.getId(),0);
        assertEquals("John", employeeDto.getForename());
        assertEquals("Smith", employeeDto.getSurname());
    }

    @Test
    public void shouldMapToEmployeeDtoList() {
        //Given
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L, "Joanne", "Clarcson"));
        employeeList.add(new Employee(2L, "Stanley", "Johnson"));
        employeeList.add(new Employee(66L, "Annie", "Flowe"));
        //When
        List<EmployeeDto> employeeDtoList = employeeMapper.mapToEmployeeDtoList(employeeList);
        //Then
        assertNotNull(employeeDtoList);
        assertEquals(3, employeeDtoList.size());
        assertTrue(employeeDtoList.get(0) instanceof EmployeeDto);
        assertEquals(1L, employeeDtoList.get(0).getId(), 0);
        assertEquals("Joanne", employeeDtoList.get(0).getForename());
        assertEquals("Clarcson", employeeDtoList.get(0).getSurname());
        assertTrue(employeeDtoList.get(1) instanceof EmployeeDto);
        assertEquals(2L, employeeDtoList.get(1).getId(), 0);
        assertEquals("Stanley", employeeDtoList.get(1).getForename());
        assertEquals("Johnson", employeeDtoList.get(1).getSurname());
        assertTrue(employeeDtoList.get(2) instanceof EmployeeDto);
        assertEquals(66L, employeeDtoList.get(2).getId(), 0);
        assertEquals("Annie", employeeDtoList.get(2).getForename());
        assertEquals("Flowe", employeeDtoList.get(2).getSurname());
    }
}