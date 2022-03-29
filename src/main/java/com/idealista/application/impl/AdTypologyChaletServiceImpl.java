package com.idealista.application.impl;

import com.idealista.application.TypeTypology;
import com.idealista.domain.Constants;
import com.idealista.domain.Typology;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdTypologyChaletServiceImpl implements TypeTypology {
    @Override
    public int calculateScore(List<String> wordsDescription) {
        return !wordsDescription.isEmpty() && wordsDescription.size() >= Constants.FIFTY ? Constants.TWENTY : 0;
    }

    @Override
    public Typology getTypology() {
        return Typology.CHALET;
    }
}
