package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

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


        Employee employee1 = new Employee("1", "A",
                new Address("Montreal", "AAAAA"));

        Address employee2Address = new Address("Mon", "BBBBB");
        Employee employee2 = new Employee("2", "B", employee2Address);


        var addressEmp3 = Address.builder().city("Tor").postalCode("GGGGGG").build();
        Employee employee3 = Employee.builder().employeeId("3")
                .name("C").address(Address.builder()
                        .city("Tor").postalCode("GGGGGG").build()).build();

        //var for fasting coding
        var result = mapper.writeValueAsString(employee1);
        System.out.println(result);

        //GENERIC
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        var resultList = mapper.writeValueAsString(employeeList);
        System.out.println(resultList);

       var dummyList = List.of(employee1, employee2, employee3);
        System.out.println(dummyList.getClass().getName());

        String jsonEmployee = "{\n" +
                "    \"employeeId\": \"1\",\n" +
                "    \"name\": \"A\",\n" +
                "    \"address\": {\n" +
                "      \"city\": \"Montreal\",\n" +
                "      \"postalCode\": \"AAAAA\"\n" +
                "    }\n" +
                "  }";

        var employeeConverted = mapper.readValue(jsonEmployee, Employee.class);
        System.out.println(employeeConverted);
    }
}