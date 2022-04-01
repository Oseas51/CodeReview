package com.idealista.application;

import com.idealista.application.impl.AdTypologyChaletServiceImpl;
import com.idealista.application.impl.AdTypologyFlatServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;

public class AdTypologyFlatServiceImplTest {

    @InjectMocks
    AdTypologyFlatServiceImpl adTypologyFlatService = new AdTypologyFlatServiceImpl();

    @Test
    public void adTypologyFlatAndDescriptionMoreThanFiftyWords () {

        int score= adTypologyFlatService.calculateScore( Arrays.asList(new String
                ("Esto es una prueba que tiene una descripcion " +
                        "de mas de cincuenta  palabras test test test test test test test test test test test test test test test test test " +
                        "test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test test").split(" ")));
        Assert.assertTrue(score== 30 );
    }

    @Test
    public void adTypologyFlatAndDescriptionMinusThanFiftyWords () {

        int score= adTypologyFlatService.calculateScore( Arrays.asList(new String
                ("Esto es una prueba que tiene una descripcion " +
                        "de mas de cincuenta  palabras test test test test test test test test test test test test test test test test test ").split(" ")));
        Assert.assertTrue(score== 10 );
    }

}
