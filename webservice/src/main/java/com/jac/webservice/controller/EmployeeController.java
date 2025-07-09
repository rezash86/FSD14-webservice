package com.jac.webservice.controller;

import com.jac.webservice.dto.Employee;
import com.jac.webservice.dto.Address;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    List<Employee> employeeList = List.of(
            new Employee("1", "A",
                    new Address("Montreal", "AAAAA")),
            new Employee("2", "B", new Address("Mon", "BBBBB")),
            Employee.builder().employeeId("3")
                    .name("C").address(Address.builder()
                            .city("Tor").postalCode("GGGGGG").build()).build()
    );

    @GetMapping("/")
    public List<Employee> getAll(){
        return employeeList;
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id){
        //Alt + enter => gives you suggestions
        Optional<Employee> first = employeeList.stream().filter(emp -> emp.getEmployeeId().equals(id)).findFirst();
        return first.orElse(null);
    }

    //let's make a route to filter the employees
    // that live in Montreal
    @GetMapping("/filter")
    public List<Employee> filterCity(@RequestParam String city){
        return employeeList.stream().filter(emp -> emp.getAddress().getCity().equals(city)).toList();
    }

    //create a post for creating a new Employee
}
