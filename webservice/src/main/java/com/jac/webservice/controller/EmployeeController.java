package com.jac.webservice.controller;

import com.jac.webservice.dto.Address;
import com.jac.webservice.dto.EmployeeDto;
import com.jac.webservice.exception.EmployeeNotFoundException;
import com.jac.webservice.mapper.EmployeeMapper;
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
@CrossOrigin
public class EmployeeController {

    private final EmployeeService service;

    private final EmployeeMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAll(){
        List<Employee> result = service.getAll();

        return ResponseEntity
                .status(OK)
                .body(result.stream().map(mapper::convertEmployeeToEmployeeDto).toList()) ;
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
                mapper.convertEmployeeToEmployeeDto(emp)).toList());
    }

    //create a post for creating a new Employee
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto){
        var domainEmp = covertEmployee(employeeDto);
        return ResponseEntity.status(CREATED).body(service.createEmployee(domainEmp));
    }


    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@RequestBody EmployeeDto employee, @PathVariable String employeeId){
        var domainEmp = covertEmployee(employee);
        var empResult = service.modifyEmployee(employeeId, domainEmp);
        var dto = mapper.convertEmployeeToEmployeeDto(empResult);
        return ResponseEntity.status(NO_CONTENT).
                body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeEmployee(@PathVariable String id){
        service.removeEmployee(id);
       return ResponseEntity.status(NO_CONTENT).body(true);
    }


    private Employee covertEmployee(EmployeeDto employeeDto) {
        return mapper.convertEmployeeDtoToEmployee(employeeDto);
    }
}
