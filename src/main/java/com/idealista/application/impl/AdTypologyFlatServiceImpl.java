package com.idealista.application.impl;

import com.idealista.application.TypeTypology;
import com.idealista.domain.Constants;
import com.idealista.domain.Typology;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class AdTypologyFlatServiceImpl implements TypeTypology {

    @Override
    public int calculateScore(List<String> wordsDescription) {
        return wordsDescription.size() >= Constants.TWENTY && wordsDescription.size() <=
                Constants.FORTY_NINE ? Constants.TEN : wordsDescription.size() >= Constants.FIFTY ?  Constants.THIRTY : Constants.ZERO;
    }

    @Override
    public Typology getTypology() {
        return Typology.FLAT;
    }


}
