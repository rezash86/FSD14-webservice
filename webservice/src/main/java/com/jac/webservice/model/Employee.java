package com.jac.webservice.model;

import com.jac.webservice.dto.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
//that is a domain object
//if you have logic in the service
public class Employee {
    private String employeeId;
    private String name;
    private Address address;
}
