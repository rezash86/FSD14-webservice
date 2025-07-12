package com.jac.webservice.service;

import com.jac.webservice.adapter.CatsApi;
import com.jac.webservice.adapter.model.CatApiDto;
import com.jac.webservice.model.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatsApi catsApi;

    public List<String> getBreeds(){
        return catsApi.getAllBreeds();
    }

    public List<Cat> getCatsByBreeds(String breeds) {
        List<CatApiDto> resultFromApi = catsApi.getCatsByBreeds(breeds);

        return resultFromApi.stream().map(result -> Cat.builder()
                .id(result.getId())
                .url(result.getUrl())
                .width(result.getWidth())
                .height(result.getHeight())
                .build()
        ).toList();
    }
}
