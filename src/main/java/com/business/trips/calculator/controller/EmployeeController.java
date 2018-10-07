package com.business.trips.calculator.controller;

import com.business.trips.calculator.controller.exception.EmployeeNotFoundException;
import com.business.trips.calculator.domain.EmployeeDto;
import com.business.trips.calculator.mapper.EmployeeMapper;
import com.business.trips.calculator.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/employees")
    public List<EmployeeDto> getEmployees() {
        return employeeMapper.mapToEmployeeDtoList(employeeRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employees/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        return employeeMapper.mapToEmployeeDto(employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) throws EmployeeNotFoundException {
        try {
            employeeRepository.delete(employeeId);
        } catch(EmptyResultDataAccessException e) {
            throw new EmployeeNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/employees")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeMapper.mapToEmployeeDto(employeeRepository.save(employeeMapper.mapToEmployee(employeeDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employees", consumes = APPLICATION_JSON_VALUE)
    public void createEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeRepository.save(employeeMapper.mapToEmployee(employeeDto));
    }
}
