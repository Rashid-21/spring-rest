package az.spring.demo.rest.rest.model.response;


import az.spring.demo.rest.rest.model.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class EmployeeResponse {
    private List<EmployeeDto> employees;

    private final String name;
    private final String surname;



}
