package com.jac.webservice.dependecyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car{
    //Association Car-> Engine
    //If you want to inject the dependency of Engine to Car
    //this was has problems !!!
    private Engine engine;

    @Autowired
    public Car(Engine engine){ //constructor injection
        this.engine = engine;
    }

    //SETTER based injection
    public void setEngine(Engine engine){
        this.engine = engine;
    }
    public void drive(){
        engine.start();
        System.out.println("Car is driving");
    }
}