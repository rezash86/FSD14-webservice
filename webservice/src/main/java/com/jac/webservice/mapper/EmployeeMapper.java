package com.jac.webservice.mapper;

import com.jac.webservice.controller.dto.AddressDto;
import com.jac.webservice.controller.dto.EmployeeDto;
import com.jac.webservice.model.Address;
import com.jac.webservice.model.Employee;
import com.jac.webservice.repository.employee.EmployeeDao;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee convertEmployeeDtoToEmployee(EmployeeDto employeeDto){
        return Employee.builder()
                .employeeId(employeeDto.getEmployeeNumber())
                .name(employeeDto.getName())
                .address(Address.builder()
                        .postalCode(employeeDto.getAddress().getPostalCode())
                        .city(employeeDto.getAddress().getCity()).build())
                .build();
    }

    public EmployeeDto convertEmployeeToEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .employeeNumber(String.valueOf(employee.getEmployeeId()))
                .name(employee.getName())
                .address(AddressDto.builder()
                        .postalCode(employee.getAddress().getPostalCode())
                        .city(employee.getAddress().getCity()).build())
                .build();
    }

    public Employee convertEmployeeDaoToEmployee(EmployeeDao dao){
        return Employee.builder()
                .employeeId(String.valueOf(dao.getId()))
                .name(dao.getName())
                .address(Address.builder()
                        .city(dao.getAddress().getCity())
                        .postalCode(dao.getAddress().getPostalCode())
                        .build())
                .build();
    }
}
