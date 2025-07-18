package com.jac.webservice.service;

import com.jac.webservice.mapper.CatMapper;
import com.jac.webservice.adapter.CatsApi;
import com.jac.webservice.adapter.dto.CatApiDto;
import com.jac.webservice.model.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatsApi catsApi;

    private final CatMapper catMapper;

    public List<String> getBreeds(){
        return catsApi.getAllBreeds();
    }

    public List<Cat> getCatsByBreeds(String breeds) {
        List<CatApiDto> resultFromApi = catsApi.getCatsByBreeds(breeds);

        return resultFromApi.stream()
                .map(result -> catMapper.convert(result)
        ).toList();
    }
}
