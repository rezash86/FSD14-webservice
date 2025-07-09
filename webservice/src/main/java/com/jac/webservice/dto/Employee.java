package com.jac.webservice.dto;
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