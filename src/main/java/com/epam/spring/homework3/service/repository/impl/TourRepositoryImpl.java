package com.epam.spring.homework3.service.repository.impl;

import com.epam.spring.homework3.service.exception.NotFoundException;
import com.epam.spring.homework3.service.model.Tour;
import com.epam.spring.homework3.service.model.enums.HotelType;
import com.epam.spring.homework3.service.model.enums.TourType;
import com.epam.spring.homework3.service.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TourRepositoryImpl implements TourRepository {

    private int id;

    private final List<Tour> tours = new ArrayList<>();

    @Override
    public List<Tour> getAllTour() {
        log.info("[Repository] successfully received tours");
        return new ArrayList<>(tours);
    }

    @Override
    public Tour getTourByName(String tourName) {
        log.info("[Repository] getTour by name {} ", tourName);
        return tours.stream()
                .filter(t -> t.getName().equals(tourName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("[Repository] Tour is not found!"));
    }

    @Override
    public List<Tour> getTourByTourType(TourType tourType) {
        log.info("[Repository] getTour by tourType {} ", tourType);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getTourType().equals(tourType))
                .toList();
        if(myTours.isEmpty()){
            throw new NotFoundException("[Repository] Tours is not found!");
        }
        return myTours;
    }

    @Override
    public List<Tour> getTourByPlaceCount(int count) {
        log.info("[Repository] getTour by place count {} ", count);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getPlaceCount() == count)
                .toList();
        if(myTours.isEmpty()){
            throw new NotFoundException("[Repository] Tours is not found!");
        }
        return myTours;
    }

    @Override
    public List<Tour> getTourByPrice(int minPrice, int maxPrice) {
        log.info("[Repository] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getPrice() > minPrice && t.getPrice() < maxPrice)
                .toList();
        if(myTours.isEmpty()){
            throw new NotFoundException("[Repository] Tours is not found!");
        }
        return myTours;
    }

    @Override
    public List<Tour> getTourByHotelType(HotelType hotelType) {
        log.info("[Repository] getTour by hotelType {} ", hotelType);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getHotel().getHotelType().equals(hotelType))
                .toList();
        if(myTours.isEmpty()){
            throw new NotFoundException("[Repository] Tours is not found!");
        }
        return myTours;
    }

    @Override
    public Tour createTour(Tour tour) {
        log.info("[Repository] createTour " + tour.getName());
        id++;
        tour.setId(id);
        tours.add(tour);
        log.info("[Repository] successfully created order with id:{}", tour.getId());
        return tour;
    }

    @Override
    public Tour updateTour(Tour tour) {
        log.info("[Repository] updateTour by all field");
        int tourId = tour.getId();
        boolean isDeleted = tours.removeIf(t -> t.getId() == tourId);
        if(isDeleted) {
            tours.add(tour);
            log.info("[Repository] updateTour is done in tour: " + tour.getId());
        }
        else{
            throw new NotFoundException("[Repository] Tour is not found!");
        }
        return tour;
    }

    @Override
    public boolean deleteTour(String tourName) {
        log.info("[Repository] deleteTour by name {} ", tourName);
        return tours.removeIf(t -> t.getName().equals(tourName));

    }
}
