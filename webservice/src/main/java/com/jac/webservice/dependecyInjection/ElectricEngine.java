package com.jac.webservice.dependecyInjection;

import org.springframework.stereotype.Component;

@Component
public class ElectricEngine extends Engine{

    @Override
    void start(){
        System.out.println("Electric car started");
    }
}
