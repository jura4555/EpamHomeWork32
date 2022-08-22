package com.epam.spring.homework4.service.repository;

import com.epam.spring.homework4.service.model.Hotel;

import java.util.List;

public interface HotelRepository {

    List<Hotel> getAllHotel();

    Hotel getHotelByName(String hotelName);

    Hotel createHotel(Hotel hotel);

    Hotel updateHotel(Hotel hotel);

    boolean deleteHotel(int hotelId);


}
