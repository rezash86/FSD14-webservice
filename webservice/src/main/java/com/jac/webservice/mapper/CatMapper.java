package com.jac.webservice.mapper;

import com.jac.webservice.adapter.dto.CatApiDto;
import com.jac.webservice.model.Cat;
import org.springframework.stereotype.Component;

@Component
public class CatMapper {

    public Cat convert(CatApiDto catDto){
        return Cat.builder()
                .id(catDto.getId())
                .url(catDto.getUrl())
                .width(catDto.getWidth())
                .height(catDto.getHeight())
                .build();
    }
}
