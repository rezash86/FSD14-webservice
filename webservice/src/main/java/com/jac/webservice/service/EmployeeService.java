package com.jac.webservice.service;

import com.jac.webservice.dto.Address;
import com.jac.webservice.dto.Employee;
import com.jac.webservice.exception.EmployeeNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

//@Component
@Service
public class EmployeeService {

    private List<Employee> employeeList;

    @PostConstruct
    void initEmployee(){
        employeeList = Stream.of(
                new Employee("1", "A",
                        new Address("Montreal", "AAAAA")),
                new Employee("2", "B", new Address("Mon", "BBBBB")),
                Employee.builder().employeeId("3")
                        .name("C").address(Address.builder()
                                .city("Tor").postalCode("GGGGGG").build()).build()
        ).collect(toCollection(ArrayList::new));

    }

    public List<Employee> getAll(){
        return employeeList;
    }

    public Employee getById(String employeeId){
        Optional<Employee> first = employeeList.stream().filter(emp -> emp.getEmployeeId().equals(employeeId)).findFirst();
        if(first.isPresent()){
            return first.get();
        }
        throw new EmployeeNotFoundException(String.format("No employee found with that employee Id -> " + employeeId));
     }

    public List<Employee> getByCity(String cityName){
        return employeeList.stream().filter(emp -> emp.getAddress().getCity().equals(cityName)).toList();
    }

    public String createEmployee(Employee employee){
        employee.setEmployeeId(String.valueOf(employeeList.size() + 1));
        employeeList.add(employee);
        return employee.getEmployeeId();
    }

    public Employee modifyEmployee(String employeeId, Employee updated){
        Optional<Employee> first = employeeList.stream().filter(emp -> emp.getEmployeeId().equals(employeeId)).findFirst();
        if(first.isPresent()){
            first.get().setName(updated.getName());
            first.get().setAddress(updated.getAddress());
            return first.get();
        }
        return null;
    }

    public boolean removeEmployee(String empId){
        Optional<Employee> first = employeeList.stream().filter(emp -> emp.getEmployeeId().equals(empId)).findFirst();
        if(first.isPresent()){
            employeeList.remove(first.get());
            return true;
        }
        return false;
    }
}
