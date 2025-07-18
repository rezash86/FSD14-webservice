package com.jac.webservice.repository.person;


import com.jac.webservice.controller.dto.Person;
import com.jac.webservice.exception.DatabaseException;
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

    public Person getOneById(int id) {
        try{
            String sql = "SELECT id, name FROM PERSON WHERE id = ?";
            var fetchResult = jdbcTemplate.query(sql, new PersonRowMapper(), id);
            if(!fetchResult.isEmpty()){
                return fetchResult.getFirst();
            }
            return null;
        }catch (Exception exception){
            throw new DatabaseException("Unexpected database operation exception " + exception.getMessage());
        }

    }

    public int createPerson(Person person) {
        String sqlQuery = "INSERT INTO(PERSON) VALUES(?)";
        jdbcTemplate.update(sqlQuery, person.getName());

        //I would like to return the id of the newly created person
        return jdbcTemplate.queryForObject("SELECT MAX(id) from PERSON", Integer.class);
    }
}
