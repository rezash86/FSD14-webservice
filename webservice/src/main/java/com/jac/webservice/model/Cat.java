package com.jac.webservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cat {
    private String id;
    private String url;
    private Integer width;
    private Integer height;
}
