package com.jac.webservice.adapter.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DogApiDto {
    private String status;
    private Map<String, List<String>> message;
}
