package az.spring.demo.rest.exception;


import az.spring.demo.rest.model.enums.ErrorCodeEnum;
import lombok.Getter;

public class CustomNotFoundException extends RuntimeException {

    @Getter
    private final int code;
    private final String message;


    public CustomNotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
