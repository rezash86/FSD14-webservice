package com.jac.webservice.controller;

import com.jac.webservice.controller.dto.Animal;
import com.jac.webservice.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;

    @GetMapping
    public Animal getRandomDog(){
        try {
            return service.getDog();
        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @PostMapping
    @RequestMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadRandomDog(@RequestParam("file") MultipartFile file){
        try {
            service.uploadPhoto(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
