package com.jac.webservice.repository.person;

import com.jac.webservice.dto.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
       return Person.builder()
               .id(rs.getInt("id"))
               .name(rs.getString("name"))
               .build();
    }
}
