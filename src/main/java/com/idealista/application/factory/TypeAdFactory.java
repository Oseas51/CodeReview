package com.idealista.application.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.idealista.application.TypeAd;
import com.idealista.application.impl.AdCompleteServiceImpl;
import com.idealista.application.impl.AdDescriptionServiceImpl;
import com.idealista.application.impl.AdPhotoServiceImpl;
import com.idealista.domain.Ad;

@Configuration
public class TypeAdFactory {
	
	@Bean
	protected TypeAd configureSteps() {
		return getPhotoService();
	}

    @Bean
    protected TypeAd getPhotoService() {
        return new AdPhotoServiceImpl(getDescriptionService());
    }
    @Bean
    protected TypeAd getDescriptionService() {
        return new AdDescriptionServiceImpl(getCompleteService());
    }
    @Bean
    protected TypeAd getCompleteService(){
        return new AdCompleteServiceImpl();
    }
    
    public void calculate(Ad ad) {
    	configureSteps().calculateScore(ad);
    }

}
