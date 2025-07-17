package com.jac.webservice.service;

import com.jac.webservice.exception.EmployeeNotFoundException;
import com.jac.webservice.model.Employee;
import com.jac.webservice.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class EmployeeService {


    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll(){
        return repository.getAll();
    }

    public Employee getById(String employeeId){
        var employeeFound = repository.getById(employeeId);
        if(employeeFound == null){
            throw new EmployeeNotFoundException(String.format("No employee found with that employee Id -> " + employeeId));
        }
        return employeeFound;
     }

    public List<Employee> getByCity(String cityName){
        return repository.getByCity(cityName);
    }

    public String createEmployee(Employee employee){
        return repository.saveEmployee(employee);
    }

    public Employee modifyEmployee(String employeeId, Employee updated){
        return repository.update(employeeId, updated);
    }

    public void removeEmployee(String empId){
        repository.remove(empId);
    }
}
