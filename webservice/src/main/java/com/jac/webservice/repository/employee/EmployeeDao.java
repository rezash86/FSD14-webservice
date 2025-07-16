package com.jac.webservice.repository.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeDao {
    private int id;
    private String name;
    private AddressDao address; // one-to-one association
}
