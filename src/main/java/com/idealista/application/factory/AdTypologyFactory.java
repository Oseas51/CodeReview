package com.idealista.application.factory;

import com.idealista.application.TypeTypology;
import com.idealista.domain.Typology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class AdTypologyFactory {

    private Map<Typology, TypeTypology> factoryTypologyServices;

    @Autowired
    public AdTypologyFactory(Set<TypeTypology> typologySet) {
        createStrategy(typologySet);
    }

    public TypeTypology findTypology(Typology typology) {
        return factoryTypologyServices.get(typology);
    }
    private void createStrategy(Set<TypeTypology> typologySet) {
        factoryTypologyServices = new HashMap<Typology, TypeTypology>();
        typologySet.forEach(
                typeTypology ->factoryTypologyServices.put(typeTypology.getTypology(), typeTypology));
    }

}
