package com.jac.webservice.adapter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DogImageDto {
    private String status;
    private List<String> message;
}
