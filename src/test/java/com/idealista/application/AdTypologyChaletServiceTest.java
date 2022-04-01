package com.idealista.application;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.idealista.application.factory.AdTypologyFactory;
import com.idealista.application.impl.AdDescriptionServiceImpl;
import com.idealista.application.impl.AdTypologyChaletServiceImpl;
import com.idealista.application.impl.AdTypologyFlatServiceImpl;
import com.idealista.domain.Constants;
import org.mockito.*;
import com.idealista.domain.Ad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AdTypologyChaletServiceTest {


    @InjectMocks
    AdTypologyChaletServiceImpl adTypologyChaletService = new AdTypologyChaletServiceImpl();

    @Test
    public void adTypologyChaletAndDescriptionMoreThanFiftyWords () {

       int score= adTypologyChaletService.calculateScore( Arrays.asList(new String
               ("Esto es una prueba que tiene una descripcion " +
                       "de mas de cincuenta  palabras test test test test test test test test test test test test test test test test test " +
                       "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test").split(" ")));
        Assert.assertTrue(score== 20 );
    }

    @Test
    public void adTypologyChaletAndDescriptionMinusThanFiftyWords () {

        int score= adTypologyChaletService.calculateScore( Arrays.asList(new String
                ("Esto es una prueba que tiene una descripcion " +
                        "de mas de cincuenta  palabras test test test test test test test test test test test test test test test test test ").split(" ")));
        Assert.assertTrue(score== 0 );
    }

}
