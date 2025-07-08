package org.example;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Employee {
    private String employeeId;
    private String name;
    private Address address;
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
class Address{
    private String city;
    private String postalCode;
}
