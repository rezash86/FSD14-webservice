package com.jac.webservice.controller.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
//is for sending or/and receiving
public class EmployeeDto {
    private String employeeNumber;
    private String name;
    private AddressDto address;
}