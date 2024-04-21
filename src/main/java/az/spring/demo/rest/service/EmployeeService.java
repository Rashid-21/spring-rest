package az.spring.demo.rest.service;

import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import az.spring.demo.rest.rest.model.response.EmployeeResponse;
import org.springframework.stereotype.Service;

public interface EmployeeService {

    EmployeeResponse getAllEmployees();

    EmployeeDto getEmployee(long id);

    EmployeeResponse getEmployeeByNameAndSurname(String name, String surname);

    EmployeeDto insert(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto, long id);

    void updateSome(EmployeeDto employeeDto, long id);

    void delete(long id);

}
