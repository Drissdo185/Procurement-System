package com.bank.digital_procurement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    PERMISSION_DENIED(1009, "Permission denied", HttpStatus.FORBIDDEN),
    DEPARTMENT_NOT_FOUND(2001, "Department not found", HttpStatus.NOT_FOUND),
    DEPARTMENT_CODE_EXISTED(2002, "Department code already exists", HttpStatus.BAD_REQUEST),
    INVALID_PARENT_DEPARTMENT(2003, "Invalid parent department", HttpStatus.BAD_REQUEST),
    DEPARTMENT_HAS_CHILDREN(2004, "Cannot delete department with children", HttpStatus.BAD_REQUEST),
    DEPARTMENT_HAS_EMPLOYEES(2005, "Cannot delete department with employees", HttpStatus.BAD_REQUEST),
    MANAGER_NOT_FOUND(2006, "Manager not found", HttpStatus.NOT_FOUND),
    CIRCULAR_DEPARTMENT_REFERENCE(2007, "Circular department reference detected", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}