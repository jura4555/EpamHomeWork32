package com.epam.spring.travel_agency.test.util;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.service.model.Hotel;
import com.epam.spring.travel_agency.service.model.enums.HotelType;

public class TestHotelDataUtil {

    public static final int HOTEL_1_ID = 1;
    public static final String HOTEL_1_NAME = "Hotel1";
    public static final String HOTEL_1_CITY = "Lviv";
    public static final HotelType HOTEL_1_TYPE = HotelType.TOURIST_CLASS;

    public static final int HOTEL_2_ID = 2;
    public static final String HOTEL_2_NAME = "Hotel2";
    public static final String HOTEL_2_CITY = "Kyiv";
    public static final HotelType HOTEL_2_TYPE = HotelType.TOURIST_CLASS;


    public static final String HOTEL_UPDATE_NAME = "Hotel3";
    public static final String HOTEL_UPDATE_CITY = "Dnipro";
    public static final HotelType HOTEL_UPDATE_TYPE = HotelType.TOURIST_CLASS;

    public static Hotel getHotel1(){
        return Hotel.builder()
                .id(HOTEL_1_ID)
                .name(HOTEL_1_NAME)
                .city(HOTEL_1_CITY)
                .hotelType(HOTEL_1_TYPE)
                .build();
    }

    public static Hotel getHotel2(){
        return Hotel.builder()
                .id(HOTEL_2_ID)
                .name(HOTEL_2_NAME)
                .city(HOTEL_2_CITY)
                .hotelType(HOTEL_2_TYPE)
                .build();
    }

    public static HotelDTO getHotelDTO1(){
        return HotelDTO.builder()
                .id(HOTEL_1_ID)
                .name(HOTEL_1_NAME)
                .city(HOTEL_1_CITY)
                .hotelType(HOTEL_1_TYPE)
                .build();
    }

    public static HotelDTO getHotelDTO2(){
        return HotelDTO.builder()
                .id(HOTEL_2_ID)
                .name(HOTEL_2_NAME)
                .city(HOTEL_2_CITY)
                .hotelType(HOTEL_2_TYPE)
                .build();
    }

    public static HotelDTO getUpdateHotelDTO(){
        return HotelDTO.builder()
                .name(HOTEL_UPDATE_NAME)
                .city(HOTEL_UPDATE_CITY)
                .hotelType(HOTEL_UPDATE_TYPE)
                .build();
    }

    public static Hotel getUpdateHotel(){
        return Hotel.builder()
                .name(HOTEL_UPDATE_NAME)
                .city(HOTEL_UPDATE_CITY)
                .hotelType(HOTEL_UPDATE_TYPE)
                .build();
    }




}
