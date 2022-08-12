package com.epam.spring.homework4.service.repository;

import com.epam.spring.homework4.service.model.Tour;
import com.epam.spring.homework4.service.model.enums.HotelType;
import com.epam.spring.homework4.service.model.enums.TourType;

import java.util.List;

public interface TourRepository {

    List<Tour> getAllTour();

    Tour getTourByName(String tourName);

    List<Tour> getTourByTourType(TourType tourType);

    List<Tour> getTourByPlaceCount(int count);

    List<Tour> getTourByPrice(int minPrice, int maxPrice);

    List<Tour> getTourByHotelType(HotelType hotelType);

    Tour createTour(Tour tour);

    Tour updateTour(Tour tour);

    boolean deleteTour(String tourName);
}
