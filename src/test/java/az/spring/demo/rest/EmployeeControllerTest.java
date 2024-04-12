package az.spring.demo.rest;

import az.spring.demo.rest.controller.EmployeeController;
import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import az.spring.demo.rest.rest.model.response.EmployeeResponse;
import az.spring.demo.rest.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }


    @Test
    void getAllEmployee() throws Exception {

        mockMvc.perform(get("/employees", 20L))
                .andExpect(status().isOk());
    }


    @Test
    void getEmployeeById() throws Exception {
        EmployeeDto employee = new EmployeeDto(1L, "Rashid", "Alakbarov", 23, 1400.0);
        when(employeeService.getEmployee(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/{employee-id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Rashid"))
                .andExpect(jsonPath("$.surname").value("Alakbarov"))
                .andExpect(jsonPath("$.age").value(23))
                .andExpect(jsonPath("$.salary").value(1400));

        verify(employeeService, times(1)).getEmployee(1L);
    }


    @Test
    void insertEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("rashid");
        employeeDto.setSurname("alakbarov");
        employeeDto.setAge(23);
        employeeDto.setSalary(1400);

        // todo Perform the POST request with the sample DTO
        mockMvc.perform(MockMvcRequestBuilders.post("/employees/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1,\"name\": \"rashid\", \"surname\": \"alakbarov\",\"age\": 23,\"salary\": 1400}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(employeeService, times(1)).insert(Mockito.eq(employeeDto));
    }


    @Test
    void delete() throws Exception {

        long employeeId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).delete(employeeId);
    }


    @Test
    void testGetEmployeeByNameAndSurname() throws Exception {
        String name = "Rashid";
        String surname = "Alakbarov";

        EmployeeResponse expectedResponse = new EmployeeResponse();
        expectedResponse.setName(name);
        expectedResponse.setSurname(surname);

        when(employeeService.getEmployeeByNameAndSurname(name, surname)).thenReturn(expectedResponse);

        mockMvc.perform(get("/employees/search")
                        .param("name", name)
                        .param("surname", surname))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Rashid"))
                .andExpect(jsonPath("$.surname").value("Alakbarov"));

        verify(employeeService, times(1)).getEmployeeByNameAndSurname(name, surname);
    }


    @Test
    void updateAll() {
        long employeeId = 1L;
        EmployeeDto expected = new EmployeeDto();
        expected.setId(employeeId);
        expected.setName("Rashid");
        expected.setSurname("Alakbarov");
        expected.setAge(23);
        expected.setSalary(1400);


        employeeController.updateAll(expected, employeeId);

        verify(employeeService, times(1)).update(expected, employeeId);
    }
}
