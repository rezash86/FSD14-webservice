package com.jac.webservice.controller;

import com.jac.webservice.dto.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    //I just had to make my list MUtable since
    // List.of gives me IMmutable list
    List<Person> people = Stream.of(
            Person.builder().id(1).name("AA").build(),
            Person.builder().id(2).name("BB").build(),
            Person.builder().id(3).name("CC").build(),
            Person.builder().id(4).name("BB").build(),
            Person.builder().id(5).name("AA").build()
    ).collect(toCollection(ArrayList::new));


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

    @GetMapping("/filter")
    public List<Person> filterName(@RequestParam String name){
        return people.stream().filter(person -> person.getName().equals(name)).toList();
    }

    @PostMapping
    public Integer createPerson(@RequestBody Person person){
        //but this is not the proper way in a web service
        //instead we need to use Log to print the errors/message/warning in the log system
        person.setId(people.size() + 1);
        people.add(person);
        return person.getId();
    }
}
