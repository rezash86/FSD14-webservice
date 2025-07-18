package com.jac.webservice.adapter.dto;

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
