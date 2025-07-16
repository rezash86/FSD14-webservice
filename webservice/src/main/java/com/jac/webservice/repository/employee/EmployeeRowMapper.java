package com.jac.webservice.repository.employee;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<EmployeeDao> {

    @Override
    public EmployeeDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EmployeeDao.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .address(AddressDao.builder()
                        .postalCode(rs.getString("postalCode"))
                        .city(rs.getString("city")).build())
                .build();
    }
}
