package com.jac.webservice.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String errorMessage){
        super(errorMessage);
    }

}
