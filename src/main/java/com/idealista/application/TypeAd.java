package com.idealista.application;

import com.idealista.domain.Ad;

public interface TypeAd {

    public abstract void calculateStore(Ad ad);

    void setNextChain(TypeAd nextChain);

}
