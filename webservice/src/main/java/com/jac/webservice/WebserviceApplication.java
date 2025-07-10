package com.jac.webservice;

import com.jac.webservice.dependecyInjection.Car;
import com.jac.webservice.dependecyInjection.ElectricEngine;
import com.jac.webservice.dependecyInjection.Engine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WebserviceApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(WebserviceApplication.class, args);
		Car car = context.getBean(Car.class);
		Engine engine = context.getBean(ElectricEngine.class);
		car.setEngine(engine);
		car.drive();
	}


}
