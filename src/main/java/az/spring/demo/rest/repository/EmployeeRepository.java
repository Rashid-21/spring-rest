package az.spring.demo.rest.repository;

import az.spring.demo.rest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameAndSurname(String name, String surname);
}
