package com.epam.spring.homework4.service.model.enums;

public enum HotelType {
    TOURIST_CLASS(1), STANDART_CLASS(2), COMFORTABLE_CLASS(3), FIRST_CLASS(4), LUXIRY(5);

    int index;

    HotelType(int i){
        index = i;
    }

    public int getIndex() {
        return index;
    }

    public static HotelType getHotelType(int i){
        return HotelType.values()[i-1];
    }
}
