package com.epam.spring.homework4.controller;

import com.epam.spring.homework4.controller.api.TourAPI;
import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.service.TourService;
import com.epam.spring.homework4.service.model.enums.HotelType;
import com.epam.spring.homework4.service.model.enums.TourType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TourController implements TourAPI {

    private final TourService tourService;


    @Override
    public List<TourDTO> getAllTour() {
        log.info("[Controller] receiving all tours");
        return tourService.getAllTour();
    }

    @Override
    public TourDTO getTourByName(String name){
        log.info("[Controller] getTour by tourName {}", name);
        return tourService.getTourByName(name);
    }

    @Override
    public List<TourDTO> getTourByTourType(TourType tourType) {
        log.info("[Controller] getTours by tourType {}", tourType);
        return tourService.getTourByTourType(tourType);
    }

    @Override
    public List<TourDTO> getTourByPlaceCount(int count) {
        log.info("[Controller] getTours by place count {}", count);
        return tourService.getTourByPlaceCount(count);
    }


    @Override
    public List<TourDTO> getTourByPrice(int minPrice, int maxPrice) {
        log.info("[Controller] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        return tourService.getTourByPrice(minPrice, maxPrice);
    }


    @Override
    public List<TourDTO> getTourByHotelType(HotelType hotelType) {
        log.info("[Controller] getTours by HotelType {}", hotelType);
        return tourService.getTourByHotelType(hotelType);
    }


    @Override
    public TourDTO createTour(TourDTO tourDTO) {
        log.info("[Controller] createTour");
        return tourService.createTour(tourDTO);
    }

    @Override
    public TourDTO updateTour(int id, TourDTO tourDTO) {
        log.info("[Controller] updateTour with all fields");
        return tourService.updateTour(id, tourDTO);
    }

    @Override
    public TourDTO updateTourBurning(int id, boolean burning) {
        log.info("[Controller] updateTour with burning field");
        return tourService.updateTourBurning(id, burning);
    }

    @Override
    public TourDTO updateTourMaxDisCount(int id, int maxDisCount) {
        log.info("[Controller] updateTour with all fields");
        return tourService.updateTourMaxDisCount(id, maxDisCount);
    }

    @Override
    public boolean deleteTour(int id) {
        log.info("[Controller] deleteTour with id {}", id);
        return tourService.deleteTour(id);
    }
}
