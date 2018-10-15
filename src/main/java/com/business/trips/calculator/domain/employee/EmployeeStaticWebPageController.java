package com.business.trips.calculator.domain.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeStaticWebPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeStaticWebPageController.class);

    @Value("${no.employees.message}")
    private String noEmployeesMessage;

    @Value("${name.too.long}")
    private String nameTooLongMessage;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping(value = {"/employees"})
    public String employeeList(Model model) {
        List<EmployeeDto> employeeDtoList = employeeController.getEmployees();
        if (employeeDtoList.size() == 0) model.addAttribute("noEmployeesMessage", noEmployeesMessage);
        model.addAttribute("employees", employeeDtoList);
        model.addAttribute("employeeForm", new EmployeeForm());
        return "employees";
    }

    @PostMapping(value = {"/employees"})
    public String addNewEmployee(Model model, @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        String forename = employeeForm.getForename();
        String surname = employeeForm.getSurname();

        if (forename != null && forename.length() > 0
                && surname != null && surname.length() > 0
                && forename.length() <= 50 && surname.length() <= 50) {
            employeeController.createEmployee(new EmployeeDto(forename, surname));
            return employeeList(model);
        }

        model.addAttribute("nameTooLongMessage", nameTooLongMessage);
        return employeeList(model);
    }

    @PostMapping(value = {"/deleteEmployee"})
    public String deleteEmployee(@RequestParam Long employeeId) {
        try {
            employeeController.deleteEmployee(employeeId);
        } catch(EmployeeNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return "redirect:/employees";
    }

    @GetMapping(value = {"/updateEmployee"})
    public String updateEmployee(Model model, @RequestParam Long employeeId) {
        try {
            EmployeeForm employeeForm = employeeMapper.mapFromEmployeeDtoToEmployeeForm(employeeController.getEmployee(employeeId));
            model.addAttribute("employeeForm", employeeForm);
        } catch (EmployeeNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return "/updateEmployee";
    }

    @PostMapping(value = {"/updateEmployee"})
    public String updateDataOfEmployee(Model model, @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        Long employeeId = employeeForm.getId();
        String forename = employeeForm.getForename();
        String surname = employeeForm.getSurname();

        if (forename != null && forename.length() > 0
                && surname != null && surname.length() > 0
                && forename.length() <= 50 && surname.length() <= 50) {
            employeeController.updateEmployee(new EmployeeDto(employeeId, forename, surname));
            return employeeList(model);
        }

        model.addAttribute("nameTooLongMessage", nameTooLongMessage);
        return employeeList(model);
    }
}
