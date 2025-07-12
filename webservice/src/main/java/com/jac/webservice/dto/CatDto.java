package com.jac.webservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDto {
    private String id;
    private String url;
    private Integer width;
    private Integer height;
}
