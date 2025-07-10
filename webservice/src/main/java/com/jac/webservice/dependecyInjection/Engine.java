package com.jac.webservice.dependecyInjection;

import org.springframework.stereotype.Component;

@Component
public class Engine{
    void start(){
        System.out.println("Engine started");
    }
}