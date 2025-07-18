package com.jac.webservice.repository.employee;

import com.jac.webservice.controller.dto.AddressDto;
import com.jac.webservice.exception.DatabaseException;
import com.jac.webservice.mapper.EmployeeMapper;
import com.jac.webservice.model.Address;
import com.jac.webservice.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final EmployeeMapper mapper;

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

    public String saveEmployee(Employee employee) {
        Address address = employee.getAddress();

        String sqlQuery = "INSERT INTO EMPLOYEE(NAME) values(?)";
        jdbcTemplate.update(sqlQuery, employee.getName());

        int employeeId = jdbcTemplate.queryForObject("SELECT MAX(id) from EMPLOYEE", Integer.class);

        sqlQuery = "INSERT INTO ADDRESS(CITY, postalCode, person_id) values(?,?, ?)";
        jdbcTemplate.update(sqlQuery, address.getCity(), address.getPostalCode(), employeeId);

        return String.valueOf(employeeId);
    }

    public boolean remove(String empId) {
        jdbcTemplate.update("DELETE FROM ADDRESS WHERE person_id= ?", empId);
        jdbcTemplate.update("DELETE FROM EMPLOYEE WHERE id=?", empId);
        return true;
    }

    public Employee update(String empld, Employee updatedEmp){
        Address address = updatedEmp.getAddress();
        jdbcTemplate.update("UPDATE ADDRESS SET city=? , postalCode=? where person_id=?", address.getCity(), address.getPostalCode(), empld);
        jdbcTemplate.update("UPDATE EMPLOYEE SET name=? where id=?", updatedEmp.getName(), empld);
        return updatedEmp;

    }

    public Employee getById(String employeeId) {
        try{
            String selectSql = "SELECT e.id, e.name, a.city, a.postalCode from employee e Left join address a ON \n" +
                    "a.person_id = e.id where e.id = ?";
            var fetchResult = jdbcTemplate.query(selectSql, new EmployeeRowMapper(), employeeId);
            if(!fetchResult.isEmpty()){
                return mapper.convertEmployeeDaoToEmployee(fetchResult.getFirst());
            }
            return null;
        }catch (Exception exception){
            throw new DatabaseException("Unexpected database operation exception " + exception.getMessage());
        }
    }

    public List<Employee> getByCity(String cityName) {
        String selectSql = "SELECT e.id, e.name, a.city, a.postalCode from employee e Left join address a ON \n" +
                "a.person_id = e.id where a.city=?";
        var fetchResult = jdbcTemplate.query(selectSql, new EmployeeRowMapper(), cityName);
        if(!fetchResult.isEmpty()){
            return fetchResult.stream().map(mapper::convertEmployeeDaoToEmployee).toList();
        }
        return null;
    }
}
