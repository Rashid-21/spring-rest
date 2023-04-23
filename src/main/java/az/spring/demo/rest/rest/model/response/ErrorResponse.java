package az.spring.demo.rest.rest.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private int code;
    private String message;
}
