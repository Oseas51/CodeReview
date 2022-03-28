package com.idealista.application;

import com.idealista.domain.Typology;

import java.util.List;

public interface TypeTypology {

    public int calculateScore (List<String> wordsDescription);

    public Typology getTypology();
}
