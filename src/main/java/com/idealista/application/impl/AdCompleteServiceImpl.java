package com.idealista.application.impl;

import com.idealista.application.TypeAd;
import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdCompleteServiceImpl extends TypeAd {

    public AdCompleteServiceImpl() {
		super(null);
	}

    @Override
    public void calculateScore(Ad ad) {
        ad.setScore(ad.isComplete() ? ad.getScore() +  Constants.FORTY : ad.getScore());
        ad.calculateAdFinal();
        checkNext(null);
    }
}
