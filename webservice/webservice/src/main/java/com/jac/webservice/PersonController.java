package com.jac.webservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @GetMapping
    public List<Person> getAll(){
        return List.of
                (
                        Person.builder().id(1).name("A").build(),
                        Person.builder().id(2).name("B").build()
                );
    }
}
