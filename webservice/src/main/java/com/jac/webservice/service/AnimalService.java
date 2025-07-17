package com.jac.webservice.service;

import com.jac.webservice.adapter.AnimalAdapter;
import com.jac.webservice.dto.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalAdapter adapter;

    public Animal getDog() throws IOException {
        var dogResult =adapter.callDogApi();
        String urlAsString = dogResult.getMessage();
        String[] urlAsArray = urlAsString.split("/"); //I can fetch the name of the dog

        URL url = new URL(dogResult.getMessage());
        InputStream in = url.openStream();
        Files.copy(in, Paths.get("src\\main\\resources\\download\\" + urlAsArray[urlAsArray.length - 1]));
        return dogResult;
    }

    public void uploadPhoto(MultipartFile file) throws IOException {
        File convertFile = new File("src\\main\\resources\\upload\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
    }
}
