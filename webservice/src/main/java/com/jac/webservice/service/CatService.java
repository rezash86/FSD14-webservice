package com.jac.webservice.service;

import com.jac.webservice.adapter.CatsApi;
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
}
