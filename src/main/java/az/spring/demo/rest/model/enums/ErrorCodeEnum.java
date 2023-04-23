package az.spring.demo.rest.model.enums;

public enum ErrorCodeEnum {
    EMPLOYEE_NOT_FOUND(1001, "Can not find employee with given id"),
    VALIDATION_ERROR(1002, " is not valid"),
    UNKNOWN_ERROR(1000, "Unknown error"), ACCESS_DENIED(1003, "Access is denied");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
