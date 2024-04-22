package az.spring.demo.rest.service.impl;

import az.spring.demo.rest.mapper.EmployeeMapper;
import az.spring.demo.rest.model.Employee;
import az.spring.demo.rest.repository.EmployeeRepository;
import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeMapper employeeMapper;

    @Test
    public void test_Insert_Employee() {
        // Mock data
        EmployeeDto employeeDto = new EmployeeDto(1, "John", "Doe",23,1200);
        Employee employee = new Employee(1, "John", "Doe",23,1200);

        // Mock behavior
        when(employeeMapper.employeeDtoToEmployee(employeeDto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.employeeToEmployeeDto(employee)).thenReturn(employeeDto);

        // Call the method
        EmployeeDto savedEmployeeDto = employeeService.insert(employeeDto);

        // Verify
        assertNotNull(savedEmployeeDto);
        assertEquals(employeeDto.getId(), savedEmployeeDto.getId());
        assertEquals(employeeDto.getName(), savedEmployeeDto.getName());
        assertEquals(employeeDto.getSurname(), savedEmployeeDto.getSurname());
        assertEquals(employeeDto.getAge(), savedEmployeeDto.getAge());
        assertEquals(employeeDto.getSalary(), savedEmployeeDto.getSalary());

        // Verify interactions
        verify(employeeMapper, times(1)).employeeDtoToEmployee(employeeDto);
        verify(employeeRepository, times(1)).save(employee);
        verify(employeeMapper, times(1)).employeeToEmployeeDto(employee);
    }


}