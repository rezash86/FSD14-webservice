package com.jac.webservice.service;

import com.jac.webservice.mapper.CatMapper;
import com.jac.webservice.adapter.CatsApi;
import com.jac.webservice.adapter.dto.CatApiDto;
import com.jac.webservice.model.Cat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {

    @InjectMocks
    private CatService underTest;

    @Mock
    private CatsApi catsApi;

    @Mock
    private CatMapper catMapper;

    @Test
    void test_getCatsByBreeds_returnsCat(){
        String testeeBreed = "persian";
        CatApiDto catApiDto1 =  CatApiDto.builder().id("1").url("url1").width(100).height(200).build();
        CatApiDto catApiDto2 = CatApiDto.builder().id("2").url("url2").width(200).height(300).build();

        var apiDtoList = List.of(catApiDto1, catApiDto2);

        when(catsApi.getCatsByBreeds(testeeBreed)).thenReturn(apiDtoList);
        var cat1 = Cat.builder().id("1").url("url1").width(100).height(200).build();
        var cat2 =  Cat.builder().id("2").url("url2").width(200).height(300).build();
        var expectedList = List.of(cat1,cat2);

        when(catMapper.convert(catApiDto1)).thenReturn(cat1);
        when(catMapper.convert(catApiDto2)).thenReturn(cat2);

        var actual  = underTest.getCatsByBreeds(testeeBreed);

        assertEquals(expectedList, actual);
    }

}
