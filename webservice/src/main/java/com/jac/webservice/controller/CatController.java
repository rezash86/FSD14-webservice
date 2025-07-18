package com.jac.webservice.controller;

import com.jac.webservice.controller.dto.CatDto;
import com.jac.webservice.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // post and send a list of breeds in the payload
    // GET and use query parameters
    // GET and use path variable

    @GetMapping("/catInfo")
    public List<CatDto> getByBreeds(@RequestParam String breeds){
        var resultCats =  catService.getCatsByBreeds(breeds);
        return resultCats.stream().map(result -> CatDto.builder()
                .id(result.getId())
                .url(result.getUrl())
                .width(result.getWidth())
                .height(result.getHeight())
                .build()
        ).toList();
    }
}
