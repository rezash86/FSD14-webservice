package com.jac.webservice.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
