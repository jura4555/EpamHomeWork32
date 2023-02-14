package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.service.TourService;
import com.epam.spring.travel_agency.service.exception.HotelNotExistsExcepton;
import com.epam.spring.travel_agency.service.exception.TourNameAlreadyExistsException;
import com.epam.spring.travel_agency.service.exception.TourNotFoundException;
import com.epam.spring.travel_agency.service.mapper.TourMapper;
import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import com.epam.spring.travel_agency.service.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    private final TourMapper tourMapper;

    @Override
    public List<TourDTO> getAllTour() {
        log.info("[Service] receiving all tours");
        List<Tour> myTour = tourRepository.findAll();
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO getTourByName(String tourName) {
        log.info("[Service] getTour by name {}", tourName);
        Tour tour = tourRepository.findByName(tourName).orElseThrow(TourNotFoundException::new);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public List<TourDTO> getTourByTourType(TourType tourType) {
        log.info("[Service] getTours by tourType {}", tourType);
        List<Tour> myTour = tourRepository.findByTourType(tourType);
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getTourByPlaceCount(int count) {
        log.info("[Service] getTours by place count {}", count);
        List<Tour> myTour = tourRepository.findByPlaceCount(count);
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TourDTO> getTourByPrice(double minPrice, double maxPrice) {
        log.info("[Service] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        List<Tour> myTour = tourRepository.findByPrice(minPrice,maxPrice);
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
        List<Tour> myTour = tourRepository.findByHotelType(hotelType);
        return myTour.stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO::getName))
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO createTour(TourDTO tourDTO) {
        log.info("[Service] createTour");
        if(tourRepository.existsByName(tourDTO.getName())){
            throw new TourNameAlreadyExistsException();
        }
        if(tourDTO.getHotel() == null){
            throw new HotelNotExistsExcepton();
        }
        Tour tour = tourMapper.mapTourDTOToTour(tourDTO);
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public TourDTO updateTour(int id, TourDTO tourDTO) {
        log.info("[Service] updateTour with all fields");
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isEmpty()){
            throw new TourNotFoundException();
        }
        if(tourDTO.getHotel() == null){
            throw new HotelNotExistsExcepton();
        }
        Tour dbTour = optionalTour.get();
        if(dbTour.getName().compareTo(tourDTO.getName()) != 0) {
            if (tourRepository.existsByName(tourDTO.getName())) {
                throw new TourNameAlreadyExistsException();
            }
        }
        Tour tour = tourMapper.mapTourDTOToTour(tourDTO);
        tour.setId(dbTour.getId());
        tour.setPlaceCount(dbTour.getPlaceCount());
        tour.setBurning(dbTour.isBurning());
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public TourDTO updateTourBurning(int id, boolean burning) {
        log.info("[Service] updateTour with burning field");
        Optional<Tour> optionalTour = tourRepository.findById(id);//тут перевірка на hotel чи присутній
        if(optionalTour.isEmpty()){
            throw new TourNotFoundException();
        }
        Tour tour = optionalTour.get();
        if(tour.getHotel() == null){
            throw new HotelNotExistsExcepton();
        }
        tour.setBurning(burning);
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    public TourDTO updateTourMaxDisCount(int id, int maxDisCount) {
        log.info("[Service] updateTour with burning field");
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isEmpty()){
            throw new TourNotFoundException();
        }
        Tour tour = optionalTour.get();
        if(tour.getHotel() == null){
            throw new HotelNotExistsExcepton();
        }
        tour.setMaxDisCount(maxDisCount);
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }


    @Override
    public void deleteTour(int tourId) {
        log.info("[Service] deleteTour with id {}", tourId);
        if(!tourRepository.existsById(tourId)){
            throw new TourNotFoundException();
        }
        tourRepository.deleteById(tourId);
    }

}
