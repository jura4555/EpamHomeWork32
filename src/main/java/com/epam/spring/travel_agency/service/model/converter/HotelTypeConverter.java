package com.epam.spring.travel_agency.service.model.converter;

import com.epam.spring.travel_agency.service.model.enums.HotelType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class HotelTypeConverter implements AttributeConverter<HotelType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(HotelType hotelType) {
        if (hotelType == null) {
            return null;
        }
        switch (hotelType) {
            case TOURIST_CLASS:
                return 1;
            case STANDART_CLASS:
                return 2;
            case COMFORTABLE_CLASS:
                return 3;
            case FIRST_CLASS:
                return 4;
            case LUXIRY:
                return 5;
            default:
                throw new IllegalArgumentException(hotelType + " not supported.");
        }
    }

    @Override
    public HotelType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        switch (dbData) {
            case 1:
                return HotelType.TOURIST_CLASS;
            case 2:
                return HotelType.STANDART_CLASS;
            case 3:
                return HotelType.COMFORTABLE_CLASS;
            case 4:
                return HotelType.FIRST_CLASS;
            case 5:
                return HotelType.LUXIRY;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
