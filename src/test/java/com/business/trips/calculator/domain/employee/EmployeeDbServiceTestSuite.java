package com.business.trips.calculator.domain.employee;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDbServiceTestSuite {

    private static Employee employee;
    private static EmployeeDto employeeDto;
    private static Optional<Employee> optionalEmployee;

    @InjectMocks
    private EmployeeDbService employeeDbService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void createEmployees() {
        employeeDto = new EmployeeDto(1L, "John", "Smith");
        employee = new Employee(1L,"John", "Smith");
        optionalEmployee = Optional.of(employee);
    }

    @Test
    public void shouldThrowExceptionByExtractingEmployee() {
        //Given
        thrown.expect(EmployeeNotFoundException.class);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenThrow(new EmployeeNotFoundException());
        //When
        employeeDbService.extractEmployeeFromDbOrThrowException(1L);
        //Then
        fail("This method should throw the EmployeeNotFoundException");
    }

    @Test
    public void shouldExtractEmployee() {
        //Given
        Mockito.when(employeeRepository.findById(1L))
                .thenReturn(optionalEmployee);
        Mockito.when(employeeMapper.mapToEmployeeDto(optionalEmployee.get())).thenReturn(employeeDto);
        //When
        EmployeeDto extractedEmployeeDto = employeeDbService.extractEmployeeFromDbOrThrowException(employee.getId());
        //Then
        assertNotNull(extractedEmployeeDto);
        assertTrue(extractedEmployeeDto instanceof EmployeeDto);
        assertEquals(1L, extractedEmployeeDto.getId(), 0);
        assertEquals("John", extractedEmployeeDto.getForename());
        assertEquals("Smith", extractedEmployeeDto.getSurname());
    }

    @Test
    public void shouldUpdateEmployeeInDb() {
        //Given
        Mockito.when(employeeRepository.findById(1L))
                .thenReturn(optionalEmployee);
        Mockito.when(employeeMapper.mapToEmployeeDto(optionalEmployee.get())).thenReturn(employeeDto);
        Mockito.when(employeeMapper.mapToEmployee(employeeDto)).thenReturn(employee);
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Mockito.when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
        //When
        EmployeeDto updatedEmployeeDto = employeeDbService.updateEmployeeInDb(employeeDto);
        //Then
        assertNotNull(updatedEmployeeDto);
        assertTrue(updatedEmployeeDto instanceof EmployeeDto);
        assertEquals(1L, updatedEmployeeDto.getId(), 0);
        assertEquals("John", updatedEmployeeDto.getForename());
        assertEquals("Smith", updatedEmployeeDto.getSurname());
    }
}