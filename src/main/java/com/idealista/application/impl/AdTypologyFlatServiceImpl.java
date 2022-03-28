package com.idealista.application.impl;

import com.idealista.application.TypeTypology;
import com.idealista.domain.Constants;
import com.idealista.domain.Typology;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdTypologyFlatServiceImpl implements TypeTypology {

    @Override
    public int calculateScore(List<String> wordsDescription) {

        int score= 0 ;
        if (wordsDescription.size() >= Constants.TWENTY && wordsDescription.size() <= Constants.FORTY_NINE) {
           score += Constants.TEN;
        }
        else if (wordsDescription.size() >= Constants.FIFTY) {
            score += Constants.THIRTY;
        }
        return score;
    }

    @Override
    public Typology getTypology() {
        return Typology.FLAT;
    }


}
