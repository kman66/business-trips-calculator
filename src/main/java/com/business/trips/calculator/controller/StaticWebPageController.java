package com.business.trips.calculator.controller;

import com.business.trips.calculator.controller.exception.EmployeeNotFoundException;
import com.business.trips.calculator.domain.EmployeeDto;
import com.business.trips.calculator.domain.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StaticWebPageController {

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    private EmployeeController employeeController;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/employeeList"}, method = RequestMethod.GET)
    public String employeeList(Model model) {
        List<EmployeeDto> employeeDtoList = employeeController.getEmployees();
        model.addAttribute("employees", employeeDtoList);
        model.addAttribute("employeeForm", new EmployeeForm());
        return "employeeList";
    }

    @RequestMapping(value = {"/employeeList"}, method = RequestMethod.POST)
    public String saveEmployee(Model model, @ModelAttribute("employeeForm") EmployeeForm employeeForm) {
        String forename = employeeForm.getForename();
        String surname = employeeForm.getSurname();

        if (forename != null && forename.length() > 0
                && surname != null && surname.length() > 0) {
            employeeController.createEmployee(new EmployeeDto(forename, surname));
            return "redirect:/employeeList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return employeeList(model);
    }

    @PostMapping(value = {"/deleteEmployee"})
    public String deleteEmployee(@RequestParam Long employeeId) {
        try {
            employeeController.deleteEmployee(employeeId);
        } catch(EmployeeNotFoundException e) {

        }
        return "redirect:/employeeList";
    }

    @RequestMapping(value = {"/updateEmployee"}, method = RequestMethod.GET)
    public String updateEmployee(Model model) {
        return "updateEmployee";
    }
}
