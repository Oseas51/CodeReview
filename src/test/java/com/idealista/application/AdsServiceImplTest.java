package com.idealista.application;

import com.idealista.application.factory.AdTypologyFactory;
import com.idealista.application.impl.AdsServiceImpl;
import com.idealista.domain.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AdsServiceImplTest {

    @Mock
    private AdRepository adRepository;

    @Spy
    private TypeAd photo;

    @Mock
    private TypeAd  description;

    @Mock
    AdTypologyFactory adTypologyFactory;

    @InjectMocks
    private AdsServiceImpl scoreService;

/*    @Test
    public void calculateScoresTest() {

        when(adRepository.findAllAds()).thenReturn(Arrays.asList(relevantAd()));
        when(photo.calculateStore(Mockito.any())).thenReturn(Integer.valueOf(5));
        when(description.calculateStore(Mockito.any())).thenReturn(Integer.valueOf(20));
        scoreService.calculateScores();
        verify(adRepository).findAllAds();
        verify(adRepository, times(1)).save(any());
    }*/

    private Ad relevantAd() {
        return new Ad(Integer.valueOf(1),
                Typology.FLAT,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras dictum felis elit, vitae cursus erat blandit vitae. Maecenas eget efficitur massa. Maecenas ut dolor eget enim consequat iaculis vitae nec elit. Maecenas eu urna nec massa feugiat pharetra. Sed eu quam imperdiet orci lobortis fermentum. Sed odio justo, congue eget iaculis.",
                Arrays.asList(new Picture(1, "http://urldeprueba.com/1", Quality.HD), new Picture(2, "http://urldeprueba.com/2", Quality.HD)),
                Integer.valueOf(50),
                null, null, null);
    }

    private Ad irrelevantAd() {
        return new Ad(Integer.valueOf(1),
                Typology.FLAT,
                "",
                Collections.emptyList(),
                Integer.valueOf(100),
                null, null, null);
    }

}