package com.jac.webservice.dependecyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Test {
    public static void main(String[] args) {
        //Engine myEngine = new Engine();
        //Engine electricEngine = new ElectricEngine();
//        Car car1 = new Car(myEngine);
//        car1.drive();
//
//        Car car2 = new Car(electricEngine);
//        car2.drive();

        ElectricEngine mock = new ElectricEngine();
        Car car = new Car(mock);
        car.drive();
    }
}






