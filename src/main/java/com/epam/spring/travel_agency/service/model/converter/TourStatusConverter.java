package com.epam.spring.travel_agency.service.model.converter;

import com.epam.spring.travel_agency.service.model.enums.TourStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TourStatusConverter implements AttributeConverter<TourStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TourStatus tourStatus) {
        if(tourStatus == null) {
            return null;
        }

        switch(tourStatus){
            case REGISTERED:
                return 1;
            case PAID:
                return 2;
            case CANCELED:
                return 3;
            default:
                throw new IllegalArgumentException(tourStatus + " not supported.");
        }
    }

    @Override
    public TourStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        switch(dbData){
            case 1:
                return TourStatus.REGISTERED;
            case 2:
                return TourStatus.PAID;
            case 3:
                return TourStatus.CANCELED;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
