package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;

import java.util.List;

public interface HotelService {

    List<HotelDTO> getAllHotel();

    HotelDTO getHotelByName(String hotelName);

    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO updateHotel(int id, HotelDTO hotelDTO);

    void deleteHotel(int hotelId);
}
