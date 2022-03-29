package com.idealista.application.impl;

import com.idealista.application.TypeAd;
import com.idealista.application.factory.AdTypologyFactory;
import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service ("description")
public class AdDescriptionServiceImpl implements TypeAd {

    @Autowired
    AdTypologyFactory adTypologyFactory;

    private TypeAd chain;

    @Override
    public void calculateStore(Ad ad) {

        Optional<String> optDesc = Optional.ofNullable(ad.getDescription());
        int score = 0;
        if (optDesc.isPresent() && !optDesc.get().isEmpty()) {
            String description = optDesc.get();
            score += Constants.FIVE;
            List<String> wds = Arrays.asList(description.split(" ")); //número de palabras
            if(!CollectionUtils.isEmpty(wds)) {
                score += adTypologyFactory.findTypology(ad.getTypology()).calculateScore(wds);
                score += wds.stream().filter(words -> Arrays.asList("luminoso", "nuevo", "céntrico", "reformado", "ático").
                        contains(words)).mapToInt(i -> Constants.FIVE).sum();
                ad.setScore(ad.getScore() + score);
            }
        }
        this.chain.calculateStore(ad);
    }

    @Override
    public void setNextChain(TypeAd nextChain) {
        this.chain = nextChain;
    }

}