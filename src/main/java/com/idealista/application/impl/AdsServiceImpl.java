package com.idealista.application.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idealista.application.AdsService;
import com.idealista.application.factory.TypeAdFactory;
import com.idealista.domain.Ad;
import com.idealista.domain.AdRepository;
import com.idealista.domain.Picture;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;

import lombok.extern.slf4j.Slf4j;

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
        typeAdFactory.calculate(ad);
        adRepository.save(ad);
        log.info("Calculate Score {} to  Ad {} " , ad.getScore() , ad.toString());
    }
}
