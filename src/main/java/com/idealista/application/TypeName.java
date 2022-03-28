package com.idealista.application;

import com.idealista.domain.Ad;


public abstract  class TypeName {

    protected TypeName successor;

    public void setSuccessor(TypeName successor) {
        this.successor = successor;
    }

    public abstract int calculateStore(Ad ad, int score);
}


