package com.idealista.application.impl;

import com.idealista.application.AdsService;
import com.idealista.application.TypeAd;
import com.idealista.domain.*;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    @Autowired
    private  AdRepository adRepository;

    @Autowired
    private TypeAd photo;

    @Autowired
    private  TypeAd description;

    @Autowired
    private  TypeAd complete;

    @Override
    public List<PublicAd> findPublicAds() {
        List<Ad> ads = adRepository.findRelevantAds();
         ads.sort(Comparator.comparing(Ad::getScore));

         return   ads.stream().map(a->new PublicAd(a.getId(),a.getTypology().toString(),
                a.getDescription(), a.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList()),
                a.getHouseSize(),a.getGardenSize())).collect(Collectors.toList());
    }

    @Override
    public List<QualityAd> findQualityAds() {

        List<Ad> ads = adRepository.findIrrelevantAds();
           return   ads.stream().map(qualityAd-> new QualityAd(qualityAd.getId(),qualityAd.getTypology().toString(),
                   qualityAd.getDescription(), qualityAd.getPictures().stream().map(Picture::getUrl).collect(Collectors.toList()),qualityAd.getHouseSize(),
                   qualityAd.getGardenSize(),
                   qualityAd.getScore(), qualityAd.getIrrelevantSince())).collect(Collectors.toList());
    }

    @Override
    public void calculateScores() {

        adRepository
                .findAllAds()
                .forEach(this::calculateScore);
    }

    private void calculateScore(Ad ad) {

        int score = Constants.ZERO;

        photo.setNextChain(description);
        description.setNextChain(complete);
        photo.calculateStore(ad);

        if (ad.getScore() < Constants.ZERO) {
            ad.setScore(Constants.ZERO);
        }
        if (ad.getScore() > Constants.ONE_HUNDRED) {
            ad.setScore(Constants.ONE_HUNDRED);
        }
        if (ad.getScore() < Constants.FORTY) {
            ad.setIrrelevantSince(new Date());
        }
        adRepository.save(ad);
    }


}
