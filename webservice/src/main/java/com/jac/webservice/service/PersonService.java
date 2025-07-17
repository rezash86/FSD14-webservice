package com.jac.webservice.service;

import com.jac.webservice.dto.Person;
import com.jac.webservice.exception.PersonNotFoundException;
import com.jac.webservice.repository.person.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@Component
public class PersonService {

    private List<Person> people;

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void init(){
        people = Stream.of(
                Person.builder().id(1).name("AA").build(),
                Person.builder().id(2).name("BB").build(),
                Person.builder().id(3).name("CC").build(),
                Person.builder().id(4).name("BB").build(),
                Person.builder().id(5).name("AA").build()
        ).collect(toCollection(ArrayList::new));
    }

    public List<Person> getAllPeople() {
        return personRepository.getAll();
    }

    public Person getPerson(int id){
        return findById(id);
    }

    private Person findById(int id){
        //we can provided some business rules
        //if no person found provide proper error message
        var fetchedPerson = personRepository.getOneById(id);
        if (fetchedPerson == null) {
            throw new PersonNotFoundException("The person not found with id : " + id);
        }
        return fetchedPerson;

    }

    public List<Person> getPersonByName(String name) {
        return people.stream().filter(p -> p.getName().equals(name)).toList();
    }

    public int createPerson(Person person) {
        if(person.getName().length() < 3){
            throw new IllegalArgumentException();
        }
        return personRepository.createPerson(person);
    }

    public Person modifyPerson(int id, Person updatePerson) {
        Person foundPerson = findById(id);
        if(foundPerson != null){
            foundPerson.setName(updatePerson.getName());
            return foundPerson;
        }
        return null;
    }

    public boolean deletePerson(int id) {
        Person foundPerson = findById(id);
        if(foundPerson != null){
            people.remove(foundPerson);
            return true;
        }
        return false;
    }
}
