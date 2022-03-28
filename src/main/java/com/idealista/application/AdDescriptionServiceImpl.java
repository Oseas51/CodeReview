package com.idealista.application;

import com.idealista.domain.Ad;
import com.idealista.domain.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            ad.setScore(ad.getScore() + score );
            List<String> wds = Arrays.asList(description.split(" ")); //número de palabras
            score += adTypologyFactory.findTypology(ad.getTypology()).calculateScore(Arrays.asList(description.split(" ")));
            ad.setScore(ad.getScore() + score );
            score +=  wds.stream().filter(words -> Arrays.asList("luminoso", "nuevo", "céntrico" , "reformado" , "ático").
                        contains(words)).mapToInt(i -> Constants.FIVE).sum();
            ad.setScore(ad.getScore() + score );
        }
        this.chain.calculateStore(ad);
    }

    @Override
    public void setNextChain(TypeAd nextChain) {
        this.chain = nextChain;
    }

}