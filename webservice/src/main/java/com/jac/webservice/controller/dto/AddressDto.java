package com.jac.webservice.controller.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddressDto {
    private String city;
    private String postalCode;
}