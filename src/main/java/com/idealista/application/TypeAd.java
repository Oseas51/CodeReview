package com.idealista.application;

import com.idealista.domain.Ad;

public abstract class TypeAd {

    protected TypeAd chain;

    public abstract void calculateScore(Ad ad);

    protected TypeAd(TypeAd next) {
        this.chain = next;
    }

    protected void checkNext(Ad ad) {
        if (null != chain && null != ad) {
            chain.calculateScore(ad);
        }
    }
}
