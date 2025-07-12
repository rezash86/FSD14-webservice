package com.jac.webservice.adapter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatApiDto {
    private String id;
    private String url;
    private Integer width;
    private Integer height;
}
