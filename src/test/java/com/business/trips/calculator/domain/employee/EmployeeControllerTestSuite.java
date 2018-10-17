package com.business.trips.calculator.domain.employee;

import com.business.trips.calculator.domain.employee.EmployeeController;
import com.business.trips.calculator.domain.employee.EmployeeDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeController employeeController;

    @Test
    public void shouldFetchEmployees() throws Exception {
        //Given
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(new EmployeeDto(1L, "John", "Bean"));
        Mockito.when(employeeController.getEmployees()).thenReturn(employeeDtoList);
        //When & Then
        mockMvc.perform(get("/v1/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].forename", Matchers.is("John")))
                .andExpect(jsonPath("$[0].surname", Matchers.is("Bean")));
    }

    @Test
    public void shouldFetchEmployee() throws Exception {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(1L, "Marie", "Blake");
        Mockito.when(employeeController.getEmployee(employeeDto.getId())).thenReturn(employeeDto);
        //When & Then
        mockMvc.perform(get("/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.forename", Matchers.is("Marie")))
                .andExpect(jsonPath("$.surname", Matchers.is("Blake")));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        //Given
        Mockito.doNothing().when(employeeController).deleteEmployee(1L);
        //When & Then
        mockMvc.perform(delete("/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
        //Given
        EmployeeDto employeeDtoBeforeUpdate = new EmployeeDto(23L, "Harry", "Wilson");
        EmployeeDto employeeDtoAfterUpdate = new EmployeeDto(23L, "Henry", "Willson");
        Mockito.when(employeeController.updateEmployee(Mockito.any(EmployeeDto.class))).thenReturn(employeeDtoAfterUpdate);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(employeeDtoBeforeUpdate);
        //When & Then
        mockMvc.perform(put("/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(23)))
                .andExpect(jsonPath("$.forename", Matchers.is("Henry")))
                .andExpect(jsonPath("$.surname", Matchers.is("Willson")));
    }

    @Test
    public void shouldCreateEmployee() throws Exception {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(4L, "Agathe", "Manson");
        Mockito.doNothing().when(employeeController).createEmployee(employeeDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(employeeDto);
        //When & Then
        mockMvc.perform(post("/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}