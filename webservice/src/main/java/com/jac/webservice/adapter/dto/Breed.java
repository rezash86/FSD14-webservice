package com.jac.webservice.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Breed {
    private String id;
    private String name;
    private String origin;
    @JsonProperty("health_issues")
    private int healthIssue;
}
