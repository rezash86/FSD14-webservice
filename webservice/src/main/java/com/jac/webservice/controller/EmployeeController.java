package com.jac.webservice.controller;

import com.jac.webservice.dto.Employee;
import com.jac.webservice.dto.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @GetMapping
    public List<Employee> getAll(){
        Employee employee1 = new Employee("1", "A",
                new Address("Montreal", "AAAAA"));

        Address employee2Address = new Address("Mon", "BBBBB");
        Employee employee2 = new Employee("2", "B", employee2Address);


        var addressEmp3 = Address.builder().city("Tor").postalCode("GGGGGG").build();
        Employee employee3 = Employee.builder().employeeId("3")
                .name("C").address(Address.builder()
                        .city("Tor").postalCode("GGGGGG").build()).build();


        //GENERIC
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        return employeeList;
    }
}
