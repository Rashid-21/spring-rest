package az.spring.demo.rest.controller;

import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import az.spring.demo.rest.rest.model.response.EmployeeResponse;
import az.spring.demo.rest.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = EmployeeController.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeServiceImpl employeeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldSaveEntitySuccessfully() throws Exception {
        EmployeeDto employee = new EmployeeDto(1L, "test", "test", 23, 1200);

        when(employeeService.insert(employee)).thenReturn(employee);

        mockMvc.perform(post("/employees/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());

        verify(employeeService, times(1)).insert(employee);
        verifyNoMoreInteractions(employeeService);
    }


    @Test
    void shouldRetrieveEmployeeById() throws Exception {
        long id = 1L;
        EmployeeDto employee = new EmployeeDto(id, "test", "test", 23, 1200);

        // TODO: 4/21/2024 thenReturn
        when(employeeService.getEmployee(id)).thenReturn(employee);

        mockMvc.perform(get("/employees/{employee-id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.surname").value("test"))
                .andExpect(jsonPath("$.age").value(23))
                .andExpect(jsonPath("$.salary").value(1200));

        verify(employeeService, times(1)).getEmployee(id);
        verifyNoMoreInteractions(employeeService);
    }


    @Test
    void shouldRetrieveEmployeeByNameAndSurname() throws Exception {
        String name = "test";
        String surname = "test";
        EmployeeResponse expectedEmployee = new EmployeeResponse(name,surname);

        when(employeeService.getEmployeeByNameAndSurname(name, surname)).thenReturn(expectedEmployee);


        mockMvc.perform(get("/employees/search")
                        .param("name", name)
                        .param("surname", surname))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.surname").value("test"));

        verify(employeeService,times(1)).getEmployeeByNameAndSurname(name,surname);
        verifyNoMoreInteractions(employeeService);
    }


    @Test
    void shouldDeleteEmployeeById() throws Exception {
        long employeeId = 1L;

        // When
        mockMvc.perform(delete("/employees/{id}", employeeId))
                .andExpect(status().isNoContent());

        // Then
        verify(employeeService, times(1)).delete(employeeId);
        verifyNoMoreInteractions(employeeService);
    }



}
