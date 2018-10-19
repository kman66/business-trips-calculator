package com.business.trips.calculator.domain.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDbService.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDto extractEmployeeFromDbOrThrowException(final Long employeeId) throws EmployeeNotFoundException {
        LOGGER.info("Extracting employee with id=" + employeeId + "...");
        EmployeeDto extractedEmployeeDto = employeeMapper.mapToEmployeeDto(employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id=" + employeeId + " has not been found.")));
        LOGGER.info("...extracted employee: " + extractedEmployeeDto);
        return extractedEmployeeDto;
    }

    public EmployeeDto updateEmployeeInDb(final EmployeeDto employeeDto) {
        EmployeeDto extractedEmployeeDto = extractEmployeeFromDbOrThrowException(employeeDto.getId());
        LOGGER.info("Updating employee: " + extractedEmployeeDto);
        EmployeeDto updatedEmployeeDto = employeeMapper.mapToEmployeeDto(employeeRepository
                .save(employeeMapper.mapToEmployee(employeeDto)));
        LOGGER.info("Employee after update: " + updatedEmployeeDto);
        return updatedEmployeeDto;
    }
}
