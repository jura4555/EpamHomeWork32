package com.epam.spring.travel_agency.test.util;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.service.model.Hotel;
import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.TourType;

import java.time.LocalDate;

public class TestTourDataUtil {

    public static final int TOUR_1_ID = 1;
    public static final String TOUR_1_NAME = "Tour1";
    public static final double TOUR_1_PRICE = 1289;
    public static final LocalDate TOUR_1_DATA_DEPARTURE = LocalDate.of(2023, 06, 12);
    public static final LocalDate TOUR_1_DATA_ARRIVAL = LocalDate.of(2023, 06, 18);
    public static final String TOUR_1_PLACE_DEPARTURE = "Lviv";
    public static final String TOUR_1_PLACE_ARRIVAL = "Kyiv";
    public static final int TOUR_1_MAX_DISCOUNT = 20;
    public static final int TOUR_1_PLACE_COUNT = 5;
    public static final TourType TOUR_1_TOUR_TYPE = TourType.REST;
    public static final boolean TOUR_1_BURNING = true;

    public static final int TOUR_2_ID = 2;
    public static final String TOUR_2_NAME = "Tour2";
    public static final double TOUR_2_PRICE = 2520;
    public static final LocalDate TOUR_2_DATA_DEPARTURE = LocalDate.of(2023, 07, 18);
    public static final LocalDate TOUR_2_DATA_ARRIVAL = LocalDate.of(2023, 07, 23);
    public static final String TOUR_2_PLACE_DEPARTURE = "Kyiv";
    public static final String TOUR_2_PLACE_ARRIVAL = "Lviv";
    public static final int TOUR_2_MAX_DISCOUNT = 30;
    public static final int TOUR_2_PLACE_COUNT = 6;
    public static final TourType TOUR_2_TOUR_TYPE = TourType.REST;
    public static final boolean TOUR_2_BURNING = false;

    public static final String TOUR_UPDATE_NAME = "Tour3";
    public static final double TOUR_UPDATE_PRICE = 3500;
    public static final LocalDate TOUR_UPDATE_DATA_DEPARTURE = LocalDate.of(2023, 07, 06);
    public static final LocalDate TOUR_UPDATE_DATA_ARRIVAL = LocalDate.of(2023, 07, 10);
    public static final String TOUR_UPDATE_PLACE_DEPARTURE = "Lviv";
    public static final String TOUR_UPDATE_PLACE_ARRIVAL = "Kyiv";
    public static final int TOUR_UPDATE_MAX_DISCOUNT = 25;
    public static final int TOUR_UPDATE_PLACE_COUNT = 7;
    public static final TourType TOUR_UPDATE_TOUR_TYPE = TourType.REST;
    public static final boolean TOUR_UPDATE_BURNING = true;

    public static final int HOTEL_UPDATE_ID = 1;

    public static Tour getTour1(){
        return Tour.builder()
                .id(TOUR_1_ID)
                .name(TOUR_1_NAME)
                .price(TOUR_1_PRICE)
                .dateDaparture(TOUR_1_DATA_DEPARTURE)
                .dateArrival(TOUR_1_DATA_ARRIVAL)
                .placeDaparture(TOUR_1_PLACE_DEPARTURE)
                .placeArrival(TOUR_1_PLACE_ARRIVAL)
                .maxDisCount(TOUR_1_MAX_DISCOUNT)
                .placeCount(TOUR_1_PLACE_COUNT)
                .hotel(TestHotelDataUtil.getHotel1())
                .tourType(TOUR_1_TOUR_TYPE)
                .burning(TOUR_1_BURNING)
                .build();
    }

    public static TourDTO getTourDTO1(){
        return TourDTO.builder()
                .id(TOUR_1_ID)
                .name(TOUR_1_NAME)
                .price(TOUR_1_PRICE)
                .dateDaparture(TOUR_1_DATA_DEPARTURE)
                .dateArrival(TOUR_1_DATA_ARRIVAL)
                .placeDaparture(TOUR_1_PLACE_DEPARTURE)
                .placeArrival(TOUR_1_PLACE_ARRIVAL)
                .maxDisCount(TOUR_1_MAX_DISCOUNT)
                .placeCount(TOUR_1_PLACE_COUNT)
                .hotel(TestHotelDataUtil.getHotelDTO1())
                .tourType(TOUR_1_TOUR_TYPE)
                .burning(TOUR_1_BURNING)
                .build();
    }


    public static Tour getTour2(){
        return Tour.builder()
                .id(TOUR_2_ID)
                .name(TOUR_2_NAME)
                .price(TOUR_2_PRICE)
                .dateDaparture(TOUR_2_DATA_DEPARTURE)
                .dateArrival(TOUR_2_DATA_ARRIVAL)
                .placeDaparture(TOUR_2_PLACE_DEPARTURE)
                .placeArrival(TOUR_2_PLACE_ARRIVAL)
                .maxDisCount(TOUR_2_MAX_DISCOUNT)
                .placeCount(TOUR_2_PLACE_COUNT)
                .hotel(TestHotelDataUtil.getHotel2())
                .tourType(TOUR_2_TOUR_TYPE)
                .burning(TOUR_2_BURNING)
                .build();
    }

    public static TourDTO getTourDTO2(){
        return TourDTO.builder()
                .id(TOUR_2_ID)
                .name(TOUR_2_NAME)
                .price(TOUR_2_PRICE)
                .dateDaparture(TOUR_2_DATA_DEPARTURE)
                .dateArrival(TOUR_2_DATA_ARRIVAL)
                .placeDaparture(TOUR_2_PLACE_DEPARTURE)
                .placeArrival(TOUR_2_PLACE_ARRIVAL)
                .maxDisCount(TOUR_2_MAX_DISCOUNT)
                .placeCount(TOUR_2_PLACE_COUNT)
                .hotel(TestHotelDataUtil.getHotelDTO2())
                .tourType(TOUR_2_TOUR_TYPE)
                .burning(TOUR_2_BURNING)
                .build();
    }

    public static Tour getTourForCreate(){
        return Tour.builder()
                .id(TOUR_1_ID)
                .name(TOUR_1_NAME)
                .price(TOUR_1_PRICE)
                .dateDaparture(TOUR_1_DATA_DEPARTURE)
                .dateArrival(TOUR_1_DATA_ARRIVAL)
                .placeDaparture(TOUR_1_PLACE_DEPARTURE)
                .placeArrival(TOUR_1_PLACE_ARRIVAL)
                .maxDisCount(0)
                .placeCount(TOUR_1_PLACE_COUNT)
                .hotel(TestHotelDataUtil.getHotel1())
                .tourType(TOUR_1_TOUR_TYPE)
                .burning(TOUR_1_BURNING)
                .build();
    }

    public static TourDTO getTourDTOForCreate(){
        return TourDTO.builder()
                .id(TOUR_1_ID)
                .name(TOUR_1_NAME)
                .price(TOUR_1_PRICE)
                .dateDaparture(TOUR_1_DATA_DEPARTURE)
                .dateArrival(TOUR_1_DATA_ARRIVAL)
                .placeDaparture(TOUR_1_PLACE_DEPARTURE)
                .placeArrival(TOUR_1_PLACE_ARRIVAL)
                .maxDisCount(0)
                .placeCount(TOUR_1_PLACE_COUNT)
                .hotel(TestHotelDataUtil.getHotelDTO1())
                .tourType(TOUR_1_TOUR_TYPE)
                .burning(TOUR_1_BURNING)
                .build();
    }

    public static Tour getTourForUpdate(){
        return Tour.builder()
                .name(TOUR_UPDATE_NAME)
                .price(TOUR_UPDATE_PRICE)
                .dateDaparture(TOUR_UPDATE_DATA_DEPARTURE)
                .dateArrival(TOUR_UPDATE_DATA_ARRIVAL)
                .placeDaparture(TOUR_UPDATE_PLACE_DEPARTURE)
                .placeArrival(TOUR_UPDATE_PLACE_ARRIVAL)
                .maxDisCount(0)
                .placeCount(TOUR_UPDATE_PLACE_COUNT)
                .hotel(Hotel.builder()
                        .id(HOTEL_UPDATE_ID)
                        .name(TestHotelDataUtil.HOTEL_UPDATE_NAME)
                        .city(TestHotelDataUtil.HOTEL_UPDATE_CITY)
                        .hotelType(TestHotelDataUtil.HOTEL_UPDATE_TYPE)
                        .build())
                .tourType(TOUR_UPDATE_TOUR_TYPE)
                .burning(TOUR_UPDATE_BURNING)
                .build();
    }

    public static TourDTO getTourDTOForUpdate(){
        return TourDTO.builder()
                .name(TOUR_UPDATE_NAME)
                .price(TOUR_UPDATE_PRICE)
                .dateDaparture(TOUR_UPDATE_DATA_DEPARTURE)
                .dateArrival(TOUR_UPDATE_DATA_ARRIVAL)
                .placeDaparture(TOUR_UPDATE_PLACE_DEPARTURE)
                .placeArrival(TOUR_UPDATE_PLACE_ARRIVAL)
                .maxDisCount(0)
                .placeCount(TOUR_UPDATE_PLACE_COUNT)
                .hotel(HotelDTO.builder()
                        .id(HOTEL_UPDATE_ID)
                        .name(TestHotelDataUtil.HOTEL_UPDATE_NAME)
                        .city(TestHotelDataUtil.HOTEL_UPDATE_CITY)
                        .hotelType(TestHotelDataUtil.HOTEL_UPDATE_TYPE)
                        .build())
                .tourType(TOUR_UPDATE_TOUR_TYPE)
                .burning(TOUR_UPDATE_BURNING)
                .build();
    }

    public static Tour getTourForOrderUpdateStatus(){
        return Tour.builder()
                .id(TOUR_1_ID)
                .name(TOUR_1_NAME)
                .price(TOUR_1_PRICE)
                .dateDaparture(TOUR_1_DATA_DEPARTURE)
                .dateArrival(TOUR_1_DATA_ARRIVAL)
                .placeDaparture(TOUR_1_PLACE_DEPARTURE)
                .placeArrival(TOUR_1_PLACE_ARRIVAL)
                .maxDisCount(TOUR_1_MAX_DISCOUNT)
                .placeCount(TOUR_1_PLACE_COUNT - 1)
                .hotel(TestHotelDataUtil.getHotel1())
                .tourType(TOUR_1_TOUR_TYPE)
                .burning(TOUR_1_BURNING)
                .build();
    }

    public static TourDTO getTourDTOForOrderUpdateStatus(){
        return TourDTO.builder()
                .id(TOUR_1_ID)
                .name(TOUR_1_NAME)
                .price(TOUR_1_PRICE)
                .dateDaparture(TOUR_1_DATA_DEPARTURE)
                .dateArrival(TOUR_1_DATA_ARRIVAL)
                .placeDaparture(TOUR_1_PLACE_DEPARTURE)
                .placeArrival(TOUR_1_PLACE_ARRIVAL)
                .maxDisCount(TOUR_1_MAX_DISCOUNT)
                .placeCount(TOUR_1_PLACE_COUNT - 1)
                .hotel(TestHotelDataUtil.getHotelDTO1())
                .tourType(TOUR_1_TOUR_TYPE)
                .burning(TOUR_1_BURNING)
                .build();
    }




}
