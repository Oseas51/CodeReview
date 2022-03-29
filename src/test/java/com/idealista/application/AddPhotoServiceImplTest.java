package com.idealista.application;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.idealista.application.impl.AdPhotoServiceImpl;
import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;

@ExtendWith(MockitoExtension.class)
public class AddPhotoServiceImplTest {

    AdPhotoServiceImpl adPhotoService = new AdPhotoServiceImpl(null);

    List<Ad> listAd = null;

    @Before
    public void setUp() throws FileNotFoundException {
        String path = "src/test/resources/ad.json";
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader( new File(path).getAbsoluteFile()));
        Ad[] arrayAd = gson.fromJson(reader, Ad[].class);
        listAd=  Arrays.asList(arrayAd);
    }

    @Test
    public void AdNotPicturesScoreMinusTenPoints () {
        Ad ad= listAd.get(0);
        adPhotoService.calculateScore(ad);
        Assert.assertNotNull(ad.getScore());
        Assert.assertTrue(ad.getScore().equals(-Constants.TEN));
    }

    @Test
    public void AdOnePictureHDScoreTwentyPoints () {

        Ad ad= listAd.get(1);
        adPhotoService.calculateScore(ad);
        Assert.assertNotNull(ad.getScore());
        Assert.assertTrue(ad.getScore().equals(Constants.TWENTY));
    }

    @Test
    public void AdOnePicturesHDAndSDScoreTwentyPoints () {

        Ad ad= listAd.get(4);
        adPhotoService.calculateScore(ad);
        Assert.assertNotNull(ad.getScore());
        Assert.assertTrue(ad.getScore().equals(Constants.THIRTY));
    }
}
