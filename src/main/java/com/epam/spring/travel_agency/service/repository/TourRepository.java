package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;

import java.util.List;

public interface TourRepository {

    List<Tour> getAllTour();

    Tour getTourById(int tourId);

    Tour getTourByName(String tourName);

    List<Tour> getTourByTourType(TourType tourType);

    List<Tour> getTourByPlaceCount(int count);

    List<Tour> getTourByPrice(int minPrice, int maxPrice);

    List<Tour> getTourByHotelType(HotelType hotelType);

    Tour createTour(Tour tour);

    Tour updateTour(Tour tour);

    void deleteTour(int tourId);
}
