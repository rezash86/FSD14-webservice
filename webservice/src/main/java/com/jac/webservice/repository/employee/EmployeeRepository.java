package com.jac.webservice.repository.employee;

import com.jac.webservice.dto.Address;
import com.jac.webservice.dto.EmployeeDto;
import com.jac.webservice.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Employee> getAll(){
        String selectSql = "SELECT e.id, e.name, a.city, a.postalCode from employee e Left join address a ON \n" +
                "a.person_id = e.id ";

        List<EmployeeDao> result =  jdbcTemplate.query(selectSql, new EmployeeRowMapper());
        return result.stream().map(emDao -> Employee.builder()
                .employeeId(String.valueOf(emDao.getId()))
                .name(emDao.getName())
                .address(Address.builder()
                        .postalCode(emDao.getAddress().getPostalCode())
                        .city(emDao.getAddress().getCity()).build())
                .build()).toList();
    }
}
