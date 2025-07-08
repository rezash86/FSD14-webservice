package org.example;

import lombok.*;

//POJO class
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private int id;
    private String name;
}
