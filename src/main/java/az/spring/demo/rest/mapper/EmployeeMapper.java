package az.spring.demo.rest.mapper;

import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import az.spring.demo.rest.model.Employee;
import az.spring.demo.rest.rest.model.response.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeDto employeeToEmployeeDto(Employee employee);


    // TODO: 4/22/2024 ? 
    List<EmployeeDto> toDtoList(List<Employee> mockEmployees);

}


