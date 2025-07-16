package com.jac.webservice.repository.person;


import com.jac.webservice.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Component
@Repository
public class PersonRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //This method will connect to the database
    //via JDBC and will fetch the person records
    public List<Person> getAll(){
        String sqlQuery = "SELECT ID, NAME FROM PERSON";
        List<Person> result = jdbcTemplate.query(sqlQuery, new PersonRowMapper());
        return result;
    }
}
