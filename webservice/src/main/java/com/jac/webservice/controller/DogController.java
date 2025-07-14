package com.jac.webservice.controller;

import com.jac.webservice.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dog")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping("/breed")
    public List<String> getAllBreeds(){
        return dogService.getAllBreeds();
    }

    @GetMapping("/breed/{breed}")
    public List<String> getBreedsInfo(@PathVariable String breed){
        return dogService.getImagesByBreed(breed);
    }
}
