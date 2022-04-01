package com.idealista.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Ad {

    private Integer id;
    private Typology typology;
    private String description;
    private List<Picture> pictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score ;
    private Date irrelevantSince;


    public boolean isComplete() {
        return (Typology.GARAGE.equals(typology) && !pictures.isEmpty())
                || (Typology.FLAT.equals(typology) && !pictures.isEmpty() && description != null && !description.isEmpty() && houseSize != null)
                || (Typology.CHALET.equals(typology) && !pictures.isEmpty() && description != null && !description.isEmpty() && houseSize != null && gardenSize != null);
    }

    public void calculateAdFinal(){

        setScore(getScore() < Constants.ZERO ? Constants.ZERO : getScore() > Constants.ONE_HUNDRED ? Constants.ONE_HUNDRED : getScore());
        setIrrelevantSince(getScore() <  Constants.FORTY? new Date(): null );
    }


}
