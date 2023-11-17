package com.epam.spring.travel_agency.controller;

import com.epam.spring.travel_agency.controller.api.TourAPI;
import com.epam.spring.travel_agency.controller.assembler.TourAssembler;
import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.controller.model.TourModel;
import com.epam.spring.travel_agency.service.TourService;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TourController implements TourAPI {

    private final TourService tourService;
    private final TourAssembler tourAssembler;

    @Override
    public Page<TourModel> getAllTour(int page, int size, String sortBy, String order) {
        log.info("[Controller] receiving all tours");
        Page<TourDTO> tourDTOs = tourService.getAllTour(page, size, sortBy, order);
        Page<TourModel> tourModels = tourDTOs.map(tourAssembler::toModel);
        return tourModels;
    }

    @Override
    public TourModel getTourByName(String name){
        log.info("[Controller] getTour by tourName {}", name);
        TourDTO tourDTO = tourService.getTourByName(name);
        return tourAssembler.toModel(tourDTO);
    }

    @Override
    public Page<TourModel> getTourByTourType(TourType tourType, int page, int size, String sortBy, String order) {
        log.info("[Controller] getTours by tourType {}", tourType);
        Page<TourDTO> tourDTOs = tourService.getTourByTourType(tourType,page, size, sortBy, order);
        Page<TourModel> tourModels = tourDTOs.map(tourAssembler::toModel);
        return tourModels;
    }

    @Override
    public Page<TourModel> getTourByPlaceCount(int count, int page, int size, String sortBy, String order) {
        log.info("[Controller] getTours by place count {}", count);
        Page<TourDTO> tourDTOs = tourService.getTourByPlaceCount(count, page, size, sortBy, order);
        Page<TourModel> tourModels = tourDTOs.map(tourAssembler::toModel);
        return tourModels;
    }


    @Override
    public Page<TourModel> getTourByPrice(double minPrice, double maxPrice, int page, int size, String sortBy, String order) {
        log.info("[Controller] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        Page<TourDTO> tourDTOs = tourService.getTourByPrice(minPrice, maxPrice, page, size, sortBy, order);
        Page<TourModel> tourModels = tourDTOs.map(tourAssembler::toModel);
        return tourModels;
    }


    @Override
    public Page<TourModel> getTourByHotelType(HotelType hotelType, int page, int size, String sortBy, String order) {
        log.info("[Controller] getTours by HotelType {}", hotelType);
        Page<TourDTO> tourDTOs = tourService.getTourByHotelType(hotelType, page, size, sortBy, order);
        Page<TourModel> tourModels = tourDTOs.map(tourAssembler::toModel);
        return tourModels;
    }


    @Override
    public TourModel createTour(TourDTO tourDTO) {
        log.info("[Controller] createTour");
        TourDTO createdTourDTO = tourService.createTour(tourDTO);
        return tourAssembler.toModel(createdTourDTO);
    }

    @Override
    public TourModel updateTour(int id, TourDTO tourDTO) {
        log.info("[Controller] updateTour with all fields");
        TourDTO updateTourDTO = tourService.updateTour(id, tourDTO);
        return tourAssembler.toModel(updateTourDTO);
    }

    @Override
    public TourModel updateTourBurning(int id, boolean burning) {
        log.info("[Controller] updateTour with burning field");
        TourDTO tourDTO = tourService.updateTourBurning(id, burning);
        return tourAssembler.toModel(tourDTO);
    }

    @Override
    public TourModel updateTourMaxDisCount(int id, int maxDisCount) {
        log.info("[Controller] updateTour with all fields");
        TourDTO tourDTO = tourService.updateTourMaxDisCount(id, maxDisCount);
        return tourAssembler.toModel(tourDTO);
    }

    @Override
    public ResponseEntity<Void> deleteTour(int id) {
        log.info("[Controller] deleteTour with id {}", id);
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }
}
