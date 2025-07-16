package com.jac.webservice.controller;

import com.jac.webservice.dto.Address;
import com.jac.webservice.dto.EmployeeDto;
import com.jac.webservice.exception.EmployeeNotFoundException;
import com.jac.webservice.model.Employee;
import com.jac.webservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EmployeeDto>> getAll(){
        //we have convesion of the domain objcect into the dto
        //in the controller
        var result = service.getAll();
        //MapperTo convert from dao to domain
        //mapper to convert from domain to dto
        return ResponseEntity.status(OK).body(result.stream().map(emp->
                EmployeeDto.builder()
                        .employeeNumber(String.valueOf(emp.getEmployeeId()))
                        .name(emp.getName())
                        .address(Address.builder()
                                .postalCode(emp.getAddress().getPostalCode())
                                .city(emp.getAddress().getCity()).build())
                        .build()).toList()) ;
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
    public ResponseEntity<List<EmployeeDto>> filterCity(@RequestParam String city){
        var result = service.getByCity(city);
        return ResponseEntity.status(OK).body(result.stream().map(emp->
                EmployeeDto.builder()
                        .employeeNumber(String.valueOf(emp.getEmployeeId()))
                        .name(emp.getName())
                        .address(Address.builder()
                                .postalCode(emp.getAddress().getPostalCode())
                                .city(emp.getAddress().getCity()).build())
                        .build()).toList());
    }

    //create a post for creating a new Employee
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employee){
        var domainEmp = Employee.builder()
                .employeeId(employee.getEmployeeNumber())
                .name(employee.getName())
                .address(Address.builder()
                        .postalCode(employee.getAddress().getPostalCode())
                        .city(employee.getAddress().getCity()).build())
                .build();
        return ResponseEntity.status(CREATED).body(service.createEmployee(domainEmp));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employee, @PathVariable String employeeId){
        var domainEmp = Employee.builder()
                .employeeId(employee.getEmployeeNumber())
                .name(employee.getName())
                .address(Address.builder()
                        .postalCode(employee.getAddress().getPostalCode())
                        .city(employee.getAddress().getCity()).build())
                .build();
        var empResult = service.modifyEmployee(employeeId, domainEmp);
        var dto = EmployeeDto.builder()
                .employeeNumber(empResult.getEmployeeId())
                .name(empResult.getName())
                .address(Address.builder()
                        .postalCode(empResult.getAddress().getPostalCode())
                        .city(empResult.getAddress().getCity()).build())
                .build();
        return ResponseEntity.status(NO_CONTENT).
                body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeEmployee(@PathVariable String id){
        service.removeEmployee(id);
       return ResponseEntity.status(NO_CONTENT).body(true);
    }
}
