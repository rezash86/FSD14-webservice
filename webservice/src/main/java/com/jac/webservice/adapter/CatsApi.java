package com.jac.webservice.adapter;

import com.jac.webservice.adapter.model.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CatsApi {

    private final String url = "https://api.thecatapi.com/v1/";
    private final RestTemplate restTemplate;

    @Autowired
    public CatsApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<String> getAllBreeds(){

       //List<Breed> result = this.restTemplate.getForObject(url.concat("breeds"), List.class);
        ResponseEntity<List<Breed>> response = restTemplate.exchange(
                url.concat("breeds"),
                HttpMethod.GET,
                null, // No request body needed for GET
                new ParameterizedTypeReference<List<Breed>>() {}
        );
       var result =  response.getBody();
       return result.stream().map(breed ->  breed.getName()).toList();
    }
}
