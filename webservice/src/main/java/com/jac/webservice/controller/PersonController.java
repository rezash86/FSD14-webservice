package com.jac.webservice.controller;

import com.jac.webservice.dto.Person;
import com.jac.webservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll() {
        var result = service.getAllPeople();
        return new ResponseEntity<>(result, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        return new ResponseEntity<>(service.getPerson(id), OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Person>> filterName(@RequestParam String name){
        var result = service.getPersonByName(name);
        return new ResponseEntity<>(result, OK);
    }

    @PostMapping
    public ResponseEntity<Integer> createPerson(@RequestBody Person person){
        return new ResponseEntity<>(service.createPerson(person), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> modifyPerson(@PathVariable int id, @RequestBody Person updatePerson){
        return new ResponseEntity<>(service.modifyPerson(id, updatePerson), NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable int id){
        return new ResponseEntity<>(service.deletePerson(id), NO_CONTENT);
    }
}
