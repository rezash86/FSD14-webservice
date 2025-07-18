package com.jac.webservice.controller;

import com.jac.webservice.controller.dto.Person;
import com.jac.webservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService personService){
        this.service = personService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll() {
        var result = service.getAllPeople();
        return new ResponseEntity<>(result, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable int id) {
        try{
            return new ResponseEntity<>(service.getPerson(id), OK);

        }catch (Exception exc){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Call to admin " + exc.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Person>> filterName(@RequestParam String name){
        var result = service.getPersonByName(name);
        return new ResponseEntity<>(result, OK);
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody Person person){
        try{
            var result = service.createPerson(person);
            return ResponseEntity.status(CREATED).body(result);
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.status(BAD_REQUEST).body("The name length needs to be more than 3 characters");
        }
        catch (Exception exc){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Call to admin " + exc.getMessage());

        }
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
