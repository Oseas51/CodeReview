package com.idealista.application.factory;

import com.idealista.application.TypeAd;
import com.idealista.application.TypeTypology;
import com.idealista.application.impl.AdCompleteServiceImpl;
import com.idealista.application.impl.AdDescriptionServiceImpl;
import com.idealista.application.impl.AdPhotoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeAdFactory {

    @Bean
    public TypeAd getPhotoService() {
        return new AdPhotoServiceImpl();
    }
    @Bean
    public TypeAd getDescriptionService() {
        return new AdDescriptionServiceImpl();
    }
    @Bean
    public TypeAd getCompleteService(){
        return new AdCompleteServiceImpl();
    }

}
