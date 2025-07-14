package com.jac.webservice.adapter;

import com.jac.webservice.adapter.model.Breed;
import com.jac.webservice.adapter.model.DogApiDto;
import com.jac.webservice.adapter.model.DogImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DogAdapter {

    @Value("${api.dog.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public List<String> getAllBreeds() {
        DogApiDto response = restTemplate.getForObject(apiUrl.concat("/breeds/list/all"), DogApiDto.class );
        if(response != null){
            Map<String, List<String>> messageMap = response.getMessage();
            return messageMap.keySet().stream().toList();
        }
        return Collections.emptyList();
    }

    public List<String> getImagesByBreed(String breed){
        DogImageDto response = restTemplate.getForObject(apiUrl.concat( "/breed/").concat(breed).concat("/images"), DogImageDto.class );
        if(response != null){
            return response.getMessage();
        }
        return Collections.emptyList();

    }

}
