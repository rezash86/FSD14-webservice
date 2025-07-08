package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ObjectMapper mapper = new ObjectMapper();
        //convert Person into a person Json
        Person person1 = new Person(1, "Jack");

        String jsonString = mapper.writeValueAsString(person1);
        System.out.println(jsonString);

        // Convert Json to Java
        jsonString = "{\"id\":2,\"name\":\"Rose\"}";
        Person personObject = mapper.readValue(jsonString, Person.class);
        System.out.println(personObject);
    }
}