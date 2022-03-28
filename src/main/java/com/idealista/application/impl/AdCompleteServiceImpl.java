package com.idealista.application.impl;

import com.idealista.application.TypeAd;
import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import org.springframework.stereotype.Service;

@Service("complete")
public class AdCompleteServiceImpl implements TypeAd {

    private TypeAd chain;

    @Override
    public void calculateStore(Ad ad) {
        int score = 0;
        if (ad.isComplete()) {
            ad.setScore(ad.getScore() +  Constants.FORTY );
        }

    }

    @Override
    public void setNextChain(TypeAd nextChain) {
        this.chain = nextChain;
    }

}
