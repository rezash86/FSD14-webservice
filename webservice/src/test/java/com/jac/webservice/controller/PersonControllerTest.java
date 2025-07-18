package com.jac.webservice.controller;


import com.jac.webservice.controller.dto.Person;
import com.jac.webservice.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    //what class Am I going to test
    @InjectMocks
    private PersonController underTest;

    @Mock
    private PersonService personService;

    ArrayList<Person> expectedPeople = Stream.of(
            Person.builder().id(1).name("A").build(),
            Person.builder().id(2).name("B").build(),
            Person.builder().id(3).name("C").build(),
            Person.builder().id(4).name("D").build(),
            Person.builder().id(5).name("E").build()
    ).collect(toCollection(ArrayList::new));

    @Test
    void test_getAll_GivesAllPeople(){
        //when we have the actual call.
        //we will have some expectation

        //provide expectation
        when(personService.getAllPeople()).thenReturn(expectedPeople);

        //real call
        ResponseEntity<List<Person>> actual = underTest.getAll();

        //assertion
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expectedPeople, actual.getBody());
    }

    @Test
    void test_getOne_givesOnePerson(){
        int testeeId = 1;

        //expectation
        Person expectedPerson = Person.builder().id(1).name("test").build();
        when(personService.getPerson(testeeId)).thenReturn(expectedPerson);
        var actual = underTest.getPerson(testeeId);

        assertEquals(expectedPerson, actual.getBody());
    }

    @Test
    void test_filterName_givesFilteredName(){
        String testeeFilter = "test";

        when(personService.getPersonByName(testeeFilter)).thenReturn(expectedPeople);

        var actual = underTest.filterName(testeeFilter);
        assertEquals(expectedPeople, actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }


}
