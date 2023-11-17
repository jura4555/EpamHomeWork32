package com.epam.spring.travel_agency.service.model.converter;

import com.epam.spring.travel_agency.service.model.enums.TourType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TourTypeConverter implements AttributeConverter<TourType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TourType tourType) {
        if(tourType == null){
            return null;
        }

        switch(tourType){
            case REST:
                return 1;
            case EXCURSION:
                return 2;
            case SHOPPING:
                return 3;
            default:
                throw new IllegalArgumentException(tourType + " not supported.");
        }
    }

    @Override
    public TourType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        switch(dbData){
            case 1:
                return TourType.REST;
            case 2:
                return TourType.EXCURSION;
            case 3:
                return TourType.SHOPPING;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");

        }
    }

}



