package com.jac.webservice.adapter;

import com.jac.webservice.adapter.dto.Breed;
import com.jac.webservice.adapter.dto.CatApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;

@Component
public class CatsApi {
    @Value("${api.cat.url}")
    private String apiUrl;
    @Value("${api.cat.api_key}")
    private String apiKey ;

    private final RestTemplate restTemplate;

    @Autowired
    public CatsApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<String> getAllBreeds(){

       //List<Breed> result = this.restTemplate.getForObject(url.concat("breeds"), List.class);
        ResponseEntity<List<Breed>> response = restTemplate.exchange(
                apiUrl.concat("breeds"),
                HttpMethod.GET,
                null, // No request body needed for GET
                new ParameterizedTypeReference<List<Breed>>() {}
        );
       var result =  response.getBody();
       return result.stream().map(breed ->  breed.getName()).toList();
    }

    public List<CatApiDto> getCatsByBreeds(String breeds) {
        String catApiUrl = apiUrl.concat("images/search?limit=20&breed_ids={0}&api_key={1}");
        String url = MessageFormat.format(catApiUrl, breeds, apiKey);
        //https://api.thecatapi.com/v1/images/search?limit=20&breed_ids={breeds}&api_key={apiKey}
        ResponseEntity<List<CatApiDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // No request body needed for GET
                new ParameterizedTypeReference<List<CatApiDto>>() {}
        );

        return response.getBody();
    }
}
