package com.jac.webservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Person {
    private int id;
    private String name;
}


//adding an Employee and show a list of Employee by creating a
//EmployeeController and return a list of Employee in your project