package com.idealista.application.impl;

import com.idealista.application.AdsService;
import com.idealista.application.TypeAd;
import com.idealista.application.factory.TypeAdFactory;
import com.idealista.domain.*;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdsServiceImpl implements AdsService {

    @Autowired
    private  AdRepository adRepository;

    @Autowired
    private TypeAdFactory typeAdFactory;

    @Override
    public List<PublicAd> findPublicAds() {
        List<Ad> ads = adRepository.findRelevantAds();
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

        log.debug("Calculate Score Ad {} " , ad.toString());

        TypeAd typeAdPhoto= typeAdFactory.getPhotoService();
        TypeAd typeAdDescription=typeAdFactory.getDescriptionService();
        TypeAd typeAdComplete=typeAdFactory.getCompleteService();

        typeAdPhoto.setNextChain(typeAdFactory.getDescriptionService());
        typeAdDescription.setNextChain(typeAdComplete);
        typeAdPhoto.calculateStore(ad);

        adRepository.save(ad.calculateAdFinal());

        log.info("Calculate Score {} to  Ad {} " , ad.getScore() , ad.toString());

    }


}
