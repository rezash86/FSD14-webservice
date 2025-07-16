package com.jac.webservice.repository.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressDao {
    private String city;
    private String postalCode;
}
