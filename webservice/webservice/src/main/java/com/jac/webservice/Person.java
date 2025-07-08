package com.jac.webservice;

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