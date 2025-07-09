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
                    Person.builder().id(1).name("AA").build(),
                    Person.builder().id(2).name("BB").build(),
                    Person.builder().id(3).name("CC").build(),
                    Person.builder().id(4).name("BB").build(),
                    Person.builder().id(5).name("AA").build()
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


    //Query parameter
    @GetMapping("/filter")
    public List<Person> filterName(@RequestParam String name){
        return people.stream().filter(person -> person.getName().equals(name)).toList();
    }
}
