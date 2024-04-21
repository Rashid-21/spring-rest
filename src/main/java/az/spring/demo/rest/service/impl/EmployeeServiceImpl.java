package az.spring.demo.rest.service.impl;

import az.spring.demo.rest.mapper.EmployeeMapper;
import az.spring.demo.rest.model.enums.ErrorCodeEnum;
import az.spring.demo.rest.exception.CustomNotFoundException;
import az.spring.demo.rest.model.Employee;
import az.spring.demo.rest.repository.EmployeeRepository;
import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import az.spring.demo.rest.rest.model.response.EmployeeResponse;
import az.spring.demo.rest.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
//        this::convertToDto

        return makeEmployeeResponse(employeeDtoList);
    }

    @Override
    public EmployeeDto getEmployee(long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::employeeToEmployeeDto)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name, String surname) {
        List<EmployeeDto> employeeList = employeeRepository.findByNameAndSurname(name, surname)
                .stream()
                .map(employeeMapper::employeeToEmployeeDto)
                .collect(Collectors.toList());

        return makeEmployeeResponse(employeeList);

    }


    @Override
    public EmployeeDto insert(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDto(savedEmployee);
    }


    @Override
    public void update(EmployeeDto employeeDto, long id) {
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());


        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDto employeeDto, long id) {
        Employee employee = getEmployeeById(id);

        if (employeeDto.getName() != null)
            employee.setName(employeeDto.getName());

        if (employeeDto.getSurname() != null)
            employee.setSurname(employeeDto.getSurname());

        if (employeeDto.getAge() > 0)
            employee.setAge(employeeDto.getAge());

        if (employeeDto.getSalary() > 0)
            employee.setSalary(employeeDto.getSalary());

        employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }


    private Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).
                orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND));
    }


    private EmployeeResponse makeEmployeeResponse(List<EmployeeDto> employees) {
        return EmployeeResponse.builder().
                employees(employees)
                .build();
    }
}

