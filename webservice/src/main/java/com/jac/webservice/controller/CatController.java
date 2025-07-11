package com.jac.webservice.controller;

import com.jac.webservice.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/breed")
    public List<String> getBreeds(){
        return catService.getBreeds();
    }
}
