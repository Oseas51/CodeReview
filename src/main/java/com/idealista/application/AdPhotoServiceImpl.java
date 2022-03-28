package com.idealista.application;

import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import com.idealista.domain.Quality;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service ("photo")
public class AdPhotoServiceImpl implements  TypeAd {

    private TypeAd chain;

    @Override
    public void calculateStore(Ad ad) {

        ad.setScore(Integer.valueOf(0));
        if (CollectionUtils.isEmpty(ad.getPictures())) {
            ad.setScore(ad.getScore() - Constants.TEN );

        }else{
            ad.setScore(ad.getScore() - ad.getPictures().stream().mapToInt(picture -> Quality.HD.equals(picture.getQuality()) ? Constants.TWENTY : Constants.TEN).sum());
        }

        this.chain.calculateStore(ad);
    }

    @Override
    public void setNextChain(TypeAd nextChain) {
        this.chain = nextChain;
    }
}