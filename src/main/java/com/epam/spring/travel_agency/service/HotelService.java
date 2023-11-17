package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelService {

    Page<HotelDTO> getAllHotel(int page, int size, String sortBy, String order);

    HotelDTO getHotelByName(String hotelName);

    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO updateHotel(int id, HotelDTO hotelDTO);

    void deleteHotel(int hotelId);
}
