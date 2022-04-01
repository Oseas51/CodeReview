package com.idealista.application.impl;

import com.idealista.application.TypeAd;
import com.idealista.application.factory.AdTypologyFactory;
import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AdDescriptionServiceImpl extends TypeAd {

    public AdDescriptionServiceImpl(TypeAd next) {
        super(next);
    }

    @Autowired
    private AdTypologyFactory adTypologyFactory;

    @Override
    public void calculateScore(Ad ad) {

        Optional<String> optDesc = Optional.ofNullable(ad.getDescription());
        int score = 0;
        if (optDesc.isPresent() && !optDesc.get().isEmpty()) {
            String description = optDesc.get();
            score += Constants.FIVE;
            List<String> wds = Arrays.asList(description.split(" ")); //número de palabras
            score += adTypologyFactory.findTypology(ad.getTypology()).calculateScore(wds);
            score += wds.stream().filter(words -> Arrays.asList("luminoso", "nuevo", "céntrico", "reformado", "ático").
                    contains(words)).mapToInt(i -> Constants.FIVE).sum();
            ad.setScore(ad.getScore() + score);
        }
        checkNext(ad);
    }

}