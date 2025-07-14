package com.jac.webservice.service;

import com.jac.webservice.adapter.DogAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogAdapter dogAdapter;

    public List<String> getAllBreeds() {
        return dogAdapter.getAllBreeds();
    }

    public List<String> getImagesByBreed(String breed){
        return dogAdapter.getImagesByBreed(breed);
    }
}
