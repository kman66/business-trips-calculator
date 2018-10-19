package com.business.trips.calculator.domain.employee;

import com.business.trips.calculator.domain.employee.EmployeeDto;
import com.business.trips.calculator.domain.employee.EmployeeNotFoundException;
import com.business.trips.calculator.domain.employee.EmployeeMapper;
import com.business.trips.calculator.domain.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeDbService employeeDbService;

    @GetMapping(value = "/employees")
    public List<EmployeeDto> getEmployees() {
        LOGGER.info("Extracting all employees from database...");
        List<EmployeeDto> employeeDtoList = employeeMapper.mapToEmployeeDtoList(employeeRepository.findAll());
        LOGGER.info("...extracted employees: " + employeeDtoList);
        return employeeDtoList;
    }

    @GetMapping(value = "/employees/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return employeeDbService.extractEmployeeFromDbOrThrowException(employeeId);
    }

    @DeleteMapping(value = "/employees/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        try {
            LOGGER.info("Deleting employee with id=" + employeeId + "...");
            employeeRepository.delete(employeeId);
            LOGGER.info("...employee with id=" + employeeId +  " successfully deleted.");
        } catch(EmptyResultDataAccessException e) {
            throw new EmployeeNotFoundException("Employee with id=" + employeeId + " has not been found.");
        }
    }

    @PutMapping(value = "/employees")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeDbService.updateEmployeeInDb(employeeDto);
    }

    @PostMapping(value = "/employees", consumes = APPLICATION_JSON_VALUE)
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        LOGGER.info("Saving new employee: " + employeeDto);
        EmployeeDto createdEmplyoeeDto = employeeMapper.getMapperFacade().map(employeeRepository
                .save(employeeMapper.getMapperFacade().mapReverse(employeeDto)));
        LOGGER.info("New employee: " + createdEmplyoeeDto + " successfully created.");
    }
}
