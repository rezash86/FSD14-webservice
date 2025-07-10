package com.jac.webservice.controller;

import com.jac.webservice.dto.Employee;
import com.jac.webservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

//    @Autowired
//    public EmployeeController(EmployeeService employeeService)
//    {
//        this.service = employeeService;
//    }


    @GetMapping("/")
    public List<Employee> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id){
       return service.getById(id);
    }

    //let's make a route to filter the employees
    // that live in Montreal
    @GetMapping("/filter")
    public List<Employee> filterCity(@RequestParam String city){
        return service.getByCity(city);
    }

    //create a post for creating a new Employee
    @PostMapping
    public String createEmployee(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    public Employee modifyEmployee(@RequestBody Employee employee, @PathVariable String employeeId){
        return service.modifyEmployee(employeeId, employee);
    }

    @DeleteMapping("/{id}")
    public boolean removeEmployee(@PathVariable String id){
       return service.removeEmployee(id);
    }
}
