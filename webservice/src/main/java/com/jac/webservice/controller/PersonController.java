package com.jac.webservice.controller;

import com.jac.webservice.dto.Person;
import com.jac.webservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    //I will have an association relationship with the personService
    private final PersonService service;
//    @Autowired
//    private PersonService service;
//    @Autowired
//    public PersonController(PersonService service) { //I want to do constructor injection
//        this.service = service;
//    }


    @GetMapping("/")
    public List<Person> getAll() {
        return service.getAllPeople();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable int id) {
        return service.getPerson(id);
    }

    @GetMapping("/filter")
    public List<Person> filterName(@RequestParam String name){
        return service.getPersonByName(name);
    }

    @PostMapping
    public Integer createPerson(@RequestBody Person person){
        return service.createPerson(person);
    }

    @PutMapping("/{id}")
    public Person modifyPerson(@PathVariable int id, @RequestBody Person updatePerson){
        return service.modifyPerson(id, updatePerson);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable int id){
        return service.deletePerson(id);
    }
}
