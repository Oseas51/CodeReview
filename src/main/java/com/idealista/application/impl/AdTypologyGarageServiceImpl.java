package com.idealista.application.impl;

import com.idealista.application.TypeTypology;
import com.idealista.domain.Typology;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdTypologyGarageServiceImpl implements TypeTypology {
    @Override
    public int calculateScore(List<String> wordsDescription) {
        return 0;
    }

    @Override
    public Typology getTypology() {
        return Typology.GARAGE;
    }
}
