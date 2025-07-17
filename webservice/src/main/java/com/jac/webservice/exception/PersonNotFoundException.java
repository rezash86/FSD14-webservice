package com.jac.webservice.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String message){
        super(message);
    }
}
