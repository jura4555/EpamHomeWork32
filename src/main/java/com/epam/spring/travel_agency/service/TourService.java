package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TourService {

    Page<TourDTO> getAllTour(int page, int size, String sortBy, String order);

    TourDTO getTourByName(String tourName);

    Page<TourDTO> getTourByTourType(TourType tourType, int page, int size, String sortBy, String order);

    Page<TourDTO> getTourByPlaceCount(int count, int page, int size, String sortBy, String order);

    Page<TourDTO> getTourByPrice(double minPrice, double maxPrice, int page, int size, String sortBy, String order);

    Page<TourDTO> getTourByHotelType(HotelType hotelType, int page, int size, String sortBy, String order);

    TourDTO createTour(TourDTO tourDTO);

    TourDTO updateTour(int id, TourDTO tourDTO);

    TourDTO updateTourBurning(int id, boolean burning);

    TourDTO updateTourMaxDisCount(int id, int maxDisCount);

    void deleteTour(int tourId);
}
