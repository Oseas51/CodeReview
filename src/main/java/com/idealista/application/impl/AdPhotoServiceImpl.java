package com.idealista.application.impl;

import com.idealista.application.TypeAd;
import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import com.idealista.domain.Quality;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
@Slf4j
public class AdPhotoServiceImpl extends TypeAd {

    public AdPhotoServiceImpl(TypeAd next) {
        super(next);
    }

    @Override
    public void calculateScore(Ad ad) {
        ad.setScore(Integer.valueOf(0));
        ad.setScore(CollectionUtils.isEmpty(ad.getPictures()) ?
                - Constants.TEN : ad.getScore() + ad.getPictures().stream().mapToInt(picture ->
                Quality.HD.equals(picture.getQuality()) ? Constants.TWENTY : Constants.TEN).sum());

        checkNext(ad);
    }


}