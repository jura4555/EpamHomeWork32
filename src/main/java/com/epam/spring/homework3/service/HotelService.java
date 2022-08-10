package com.epam.spring.homework3.service;

import com.epam.spring.homework3.controller.dto.HotelDTO;

import java.util.List;

public interface HotelService {

    List<HotelDTO> getAllHotel();

    HotelDTO getHotelByName(String hotelName);

    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO updateHotel(HotelDTO hotelDTO);

    boolean deleteHotel(int hotelId);
}
