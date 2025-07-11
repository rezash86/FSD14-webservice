package com.jac.webservice.controller;

import com.jac.webservice.dto.Employee;
import com.jac.webservice.exception.EmployeeNotFoundException;
import com.jac.webservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


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
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.status(OK).body(service.getAll()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
       try{
           var foundEmployee =  service.getById(id);
           return ResponseEntity.status(OK).body(foundEmployee);
       }catch (EmployeeNotFoundException e){
           return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
       }
       catch (Exception e){
           return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Call to adminstrator");
       }

    }

    //let's make a route to filter the employees
    // that live in Montreal
    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> filterCity(@RequestParam String city){
        return ResponseEntity.status(OK).body(service.getByCity(city));
    }

    //create a post for creating a new Employee
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.status(CREATED).body(service.createEmployee(employee));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee employee, @PathVariable String employeeId){
        return ResponseEntity.status(NO_CONTENT).body(service.modifyEmployee(employeeId, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeEmployee(@PathVariable String id){
        service.removeEmployee(id);
       return ResponseEntity.status(NO_CONTENT).body(true);
    }
}
