package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.Hotel;

import java.util.List;

public interface HotelRepository {

    List<Hotel> getAllHotel();

    Hotel getHotelByName(String hotelName);

    Hotel createHotel(Hotel hotel);

    Hotel updateHotel(Hotel hotel);

    void deleteHotel(int hotelId);


}
