package com.epam.spring.homework4.service;

import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.service.model.enums.HotelType;
import com.epam.spring.homework4.service.model.enums.TourType;

import java.util.List;

public interface TourService {

    List<TourDTO> getAllTour();

    TourDTO getTourByName(String tourName);

    List<TourDTO> getTourByTourType(TourType tourType);

    List<TourDTO> getTourByPlaceCount(int count);

    List<TourDTO> getTourByPrice(int minPrice, int maxPrice);

    List<TourDTO> getTourByHotelType(HotelType hotelType);

    TourDTO createTour(TourDTO tourDTO);

    TourDTO updateTour(int id, TourDTO tourDTO);

    TourDTO updateTourBurning(int id, boolean burning);

    TourDTO updateTourMaxDisCount(int id, int maxDisCount);

    void deleteTour(int tourId);
}
