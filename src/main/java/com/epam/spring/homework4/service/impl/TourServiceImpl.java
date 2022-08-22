package com.epam.spring.homework4.service.impl;

import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.service.TourService;
import com.epam.spring.homework4.service.mapper.TourMapper;
import com.epam.spring.homework4.service.model.Tour;
import com.epam.spring.homework4.service.model.enums.HotelType;
import com.epam.spring.homework4.service.model.enums.TourType;
import com.epam.spring.homework4.service.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    private final TourMapper tourMapper;

    @Override
    public List<TourDTO> getAllTour() {
        log.info("[Service] receiving all tours");
        return tourRepository.getAllTour().stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO getTourByName(String tourName) {
        log.info("[Service] getTour by name {}", tourName);
        Tour tour = tourRepository.getTourByName(tourName);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public List<TourDTO> getTourByTourType(TourType tourType) {
        log.info("[Service] getTours by tourType {}", tourType);
        List<Tour> myTour = tourRepository.getTourByTourType(tourType);
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getTourByPlaceCount(int count) {
        log.info("[Service] getTours by place count {}", count);
        List<Tour> myTour = tourRepository.getTourByPlaceCount(count);
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getTourByPrice(int minPrice, int maxPrice) {
        log.info("[Service] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        List<Tour> myTour = tourRepository.getTourByPrice(minPrice,maxPrice);
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO::getPrice))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getTourByHotelType(HotelType hotelType) {
        log.info("[Service] getTours by HotelType {}", hotelType);
        List<Tour> myTour = tourRepository.getTourByHotelType(hotelType);
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO createTour(TourDTO tourDTO) {
        log.info("[Service] createTour");
        Tour tour = tourMapper.mapTourDTOToTour(tourDTO);
        tour = tourRepository.createTour(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public TourDTO updateTour(int id, TourDTO tourDTO) {
        log.info("[Service] updateTour with all fields");
        Tour tour = tourMapper.mapTourDTOToTour(tourDTO);
        tour.setId(id);
        Tour tourPlace = tourRepository.getTourById(id);
        tour.setPlaceCount(tourPlace.getPlaceCount());
        tour = tourRepository.updateTour(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public TourDTO updateTourBurning(int id, boolean burning) {
        log.info("[Service] updateTour with burning field");
        Tour tour = tourRepository.getTourById(id);
        tour.setBurning(burning);
        tour = tourRepository.updateTour(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public TourDTO updateTourMaxDisCount(int id, int maxDisCount) {
        log.info("[Service] updateTour with burning field");
        Tour tour = tourRepository.getTourById(id);
        tour.setMaxDisCount(maxDisCount);
        tour = tourRepository.updateTour(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }


    @Override
    public boolean deleteTour(int tourId) {
        log.info("[Service] deleteTour with id {}", tourId);
        return tourRepository.deleteTour(tourId);
    }


}
