package com.jac.webservice.adapter.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatApiDto {
    private String id;
    private String url;
    private Integer width;
    private Integer height;
}
