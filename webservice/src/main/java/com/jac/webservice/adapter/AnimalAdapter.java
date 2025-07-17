package com.jac.webservice.adapter;

import com.jac.webservice.dto.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AnimalAdapter {

    private final RestTemplate restTemplate;

    public Animal callDogApi(){
        String url = "https://dog.ceo/api/breeds/image/random";
        Animal dog = restTemplate.getForObject(url, Animal.class);
        return dog;
    }
}
