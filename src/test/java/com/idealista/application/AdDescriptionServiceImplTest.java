package com.idealista.application;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.idealista.application.factory.AdTypologyFactory;
import com.idealista.application.impl.AdDescriptionServiceImpl;
import com.idealista.application.impl.AdTypologyChaletServiceImpl;
import com.idealista.application.impl.AdTypologyFlatServiceImpl;
import org.mockito.*;
import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdDescriptionServiceImplTest {

    @Mock
    AdTypologyFactory adTypologyFactory;

    @InjectMocks
    AdDescriptionServiceImpl adDescriptionService = new AdDescriptionServiceImpl(null);

    List<Ad> listAd = null;

    @Before
    public void setUp() throws FileNotFoundException {
        String path = "src/test/resources/ad.json";
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader( new File(path).getAbsoluteFile()));
        Ad[] arrayAd = gson.fromJson(reader, Ad[].class);
        listAd=  Arrays.asList(arrayAd);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AdNotHaveDescriptionScoreZeroPoints () {
        Ad ad= listAd.get(2);
        adDescriptionService.calculateScore(ad);
        Assert.assertNull(ad.getScore());
    }


}
