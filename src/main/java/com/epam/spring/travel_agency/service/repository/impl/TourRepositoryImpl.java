package com.epam.spring.travel_agency.service.repository.impl;

import com.epam.spring.travel_agency.service.exception.TourNotFoundException;
import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import com.epam.spring.travel_agency.service.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Tour getTourById(int tourId) {
        log.info("[Repository] getTour by id {} ", tourId);
        return tours.stream()
                .filter(t -> t.getId() == tourId)
                .findFirst()
                .orElseThrow(() -> new TourNotFoundException());
    }

    @Override
    public Tour getTourByName(String tourName) {
        log.info("[Repository] getTour by name {} ", tourName);
        return tours.stream()
                .filter(t -> t.getName().equals(tourName))
                .findFirst()
                .orElseThrow(() -> new TourNotFoundException());
    }

    @Override
    public List<Tour> getTourByTourType(TourType tourType) {
        log.info("[Repository] getTour by tourType {} ", tourType);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getTourType().equals(tourType))
                .collect(Collectors.toList());
        if(myTours.isEmpty()){
            throw new TourNotFoundException();
        }
        return myTours;
    }

    @Override
    public List<Tour> getTourByPlaceCount(int count) {
        log.info("[Repository] getTour by place count {} ", count);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getPlaceCount() == count)
                .collect(Collectors.toList());
        if(myTours.isEmpty()){
            throw new TourNotFoundException();
        }
        return myTours;
    }

    @Override
    public List<Tour> getTourByPrice(int minPrice, int maxPrice) {
        log.info("[Repository] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getPrice() > minPrice && t.getPrice() < maxPrice)
                .collect(Collectors.toList());
        if(myTours.isEmpty()){
            throw new TourNotFoundException();
        }
        return myTours;
    }

    @Override
    public List<Tour> getTourByHotelType(HotelType hotelType) {
        log.info("[Repository] getTour by hotelType {} ", hotelType);
        List<Tour> myTours = tours.stream()
                .filter(t -> t.getHotel().getHotelType().equals(hotelType))
                .collect(Collectors.toList());
        if(myTours.isEmpty()){
            throw new TourNotFoundException();
        }
        return myTours;
    }

    @Override
    public Tour createTour(Tour tour) {
        log.info("[Repository] createTour " + tour.getId());
        id++;
        tour.setId(id);
        tours.add(tour);
        log.info("[Repository] successfully tour order with id:{}", tour.getId());
        return tour;
    }

    @Override
    public Tour updateTour(Tour tour) {
        log.info("[Repository] updateTour by all field");
        boolean isDeleted = tours.removeIf(t -> t.getId() == tour.getId());
        if(isDeleted) {
            tours.add(tour);
            log.info("[Repository] updateTour is done in tour: " + tour.getId());
        }
        else{
            throw new TourNotFoundException();
        }
        return tour;
    }

    @Override
    public void deleteTour(int tourId) {
        log.info("[Repository] deleteTour by id {} ", tourId);
        tours.removeIf(t -> t.getId() == tourId);
    }
}
