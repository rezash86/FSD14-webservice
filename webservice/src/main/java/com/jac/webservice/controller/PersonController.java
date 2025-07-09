package com.jac.webservice.controller;

import com.jac.webservice.dto.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    List<Person> people = List.of
            (
                    Person.builder().id(1).name("A").build(),
                    Person.builder().id(2).name("B").build()
            );


    @GetMapping("/")
    public List<Person> getAll() {
        return people;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable int id) {
        Optional<Person> optionalPerson = people.stream()
                .filter(person -> person.getId() == id).findFirst();
        return optionalPerson.orElse(null);
    }
}
