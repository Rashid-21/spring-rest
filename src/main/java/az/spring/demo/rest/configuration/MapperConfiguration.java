package az.spring.demo.rest.configuration;

import az.spring.demo.rest.mapper.EmployeeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MapperConfiguration {

    @Bean
    EmployeeMapper employeeMapper(){
        return EmployeeMapper.INSTANCE;
    }
}
