package com.jac.webservice.service;

import com.jac.webservice.dto.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@Component
public class PersonService {

    public List<Person> getAllPeople() {
        return Stream.of(
                Person.builder().id(1).name("AA").build(),
                Person.builder().id(2).name("BB").build(),
                Person.builder().id(3).name("CC").build(),
                Person.builder().id(4).name("BB").build(),
                Person.builder().id(5).name("AA").build()
        ).collect(toCollection(ArrayList::new));
    }

    public Person getPerson(int id){
        Optional<Person> optionalPerson = findById(id);;
        return optionalPerson.orElse(null);
    }

    private Optional<Person> findById(int id){
        return getAllPeople().stream().filter(p -> p.getId() == id).findFirst();

    }

    public List<Person> getPersonByName(String name) {
        return getAllPeople().stream().filter(p -> p.getName().equals(name)).toList();
    }

    public int createPerson(Person person) {
        var people = getAllPeople();

        person.setId(people.size() + 1);
        people.add(person);
        return person.getId();
    }

    public Person modifyPerson(int id, Person updatePerson) {
        Optional<Person> foundPerson = findById(id);
        if(foundPerson.isPresent()){
            foundPerson.get().setName(updatePerson.getName());
            return foundPerson.get();
        }
        return null;
    }

    public boolean deletePerson(int id) {
        Optional<Person> foundPerson = findById(id);
        if(foundPerson.isPresent()){
            getAllPeople().remove(foundPerson.get());
            return true;
        }
        return false;
    }
}
