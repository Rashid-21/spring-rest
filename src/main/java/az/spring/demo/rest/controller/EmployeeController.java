package az.spring.demo.rest.controller;


import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import az.spring.demo.rest.rest.model.response.EmployeeResponse;
import az.spring.demo.rest.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employee services", description = "employee services")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping()
    public EmployeeResponse getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/{employee-id}")
    @Operation(summary = "This gets employee by id")
    @ApiResponse(description = "ok", responseCode = "200")
    public EmployeeDto getEmployee(@PathVariable("employee-id") long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/search")
    public EmployeeResponse getEmployeeByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname) {

        return employeeService.getEmployeeByNameAndSurname(name, surname);

    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody EmployeeDto employeeDto) {
        employeeService.insert(employeeDto);
    }

    @PutMapping("/{id}")
    public void updateAll(@RequestBody EmployeeDto employeeDto, @PathVariable("id") long id) {
        employeeService.update(employeeDto, id);
    }

    @PatchMapping("/{id}")
    public void updateSome(@RequestBody EmployeeDto employeeDto, @PathVariable("id") long id) {
        employeeService.updateSome(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        employeeService.delete(id);
    }


}
