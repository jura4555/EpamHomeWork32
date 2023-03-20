package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.service.TourService;
import com.epam.spring.travel_agency.service.exception.DateException;
import com.epam.spring.travel_agency.service.exception.HotelNotExistsException;
import com.epam.spring.travel_agency.service.exception.TourNameAlreadyExistsException;
import com.epam.spring.travel_agency.service.exception.TourNotFoundException;
import com.epam.spring.travel_agency.service.mapper.TourMapper;
import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import com.epam.spring.travel_agency.service.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Page<TourDTO> getAllTour(int page, int size, String sortBy, String order) {
        log.info("[Service] receiving all tours");
        List<Tour> tours = tourRepository.findAll();

        Pageable pageableSorted = PageRequest.of(0, tours.size(),
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());

        Page<Tour> pageAllTour = tourRepository.findAll(pageableSorted);

        List<TourDTO> myTour = pageAllTour.getContent().stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), myTour.size());
        Page <TourDTO> tourDTOS = new PageImpl<>(myTour.subList(start, end), pageable, myTour.size());
        return tourDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public TourDTO getTourByName(String tourName) {
        log.info("[Service] getTour by name {}", tourName);
        Tour tour = tourRepository.findByName(tourName).orElseThrow(TourNotFoundException::new);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TourDTO> getTourByTourType(TourType tourType, int page, int size, String sortBy, String order) {
        log.info("[Service] getTours by tourType {}", tourType);
        List<Tour> tours = tourRepository.findByTourType(tourType);

        Pageable pageableSorted = PageRequest.of(0, tours.size(),
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());

        Page<Tour> pageAllTour = tourRepository.findByTourType(tourType, pageableSorted);

        List<TourDTO> myTour = pageAllTour.getContent().stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), myTour.size());
        Page <TourDTO> tourDTOS = new PageImpl<>(myTour.subList(start, end), pageable, myTour.size());
        return tourDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TourDTO> getTourByPlaceCount(int count, int page, int size, String sortBy, String order) {
        log.info("[Service] getTours by place count {}", count);
        List<Tour> tours = tourRepository.findByPlaceCount(count);

        Pageable pageableSorted = PageRequest.of(0, tours.size(),
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());

        Page<Tour> pageAllTour = tourRepository.findByPlaceCount(count, pageableSorted);

        List<TourDTO> myTour = pageAllTour.getContent().stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), myTour.size());
        Page <TourDTO> tourDTOS = new PageImpl<>(myTour.subList(start, end), pageable, myTour.size());
        return tourDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TourDTO> getTourByPrice(double minPrice, double maxPrice, int page, int size, String sortBy, String order) {
        log.info("[Service] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        List<Tour> tours = tourRepository.findByPrice(minPrice, maxPrice);

        Pageable pageableSorted = PageRequest.of(0, tours.size(),
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());

        Page<Tour> pageAllTour = tourRepository.findByPrice(minPrice, maxPrice, pageableSorted);

        List<TourDTO> myTour = pageAllTour.getContent().stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), myTour.size());
        Page <TourDTO> tourDTOS = new PageImpl<>(myTour.subList(start, end), pageable, myTour.size());
        return tourDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TourDTO> getTourByHotelType(HotelType hotelType, int page, int size, String sortBy, String order) {
        log.info("[Service] getTours by HotelType {}", hotelType);
        List<Tour> tours = tourRepository.findByHotelType(hotelType);

        Pageable pageableSorted = PageRequest.of(0, tours.size(),
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());

        Page<Tour> pageAllTour = tourRepository.findByHotelType(hotelType, pageableSorted);

        List<TourDTO> myTour = pageAllTour.getContent().stream()
                .map(tourMapper::mapTourToTourDTO)
                .sorted(Comparator.comparing(TourDTO -> !TourDTO.isBurning()))
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), myTour.size());
        Page <TourDTO> tourDTOS = new PageImpl<>(myTour.subList(start, end), pageable, myTour.size());
        return tourDTOS;
    }

    @Override
    @Transactional
    public TourDTO createTour(TourDTO tourDTO) {
        log.info("[Service] createTour");
        if(tourRepository.existsByName(tourDTO.getName())){
            throw new TourNameAlreadyExistsException();
        }
        if(tourDTO.getHotel() == null){
            throw new HotelNotExistsException();
        }
        if( tourDTO.getDateArrival().compareTo(tourDTO.getDateDaparture()) == 0 ||
                tourDTO.getDateArrival().isBefore(tourDTO.getDateDaparture())){
            throw new DateException();
        }
        Tour tour = tourMapper.mapTourDTOToTour(tourDTO);
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    @Transactional
    public TourDTO updateTour(int id, TourDTO tourDTO) {
        log.info("[Service] updateTour with all fields");
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isEmpty()){
            throw new TourNotFoundException();
        }
        if(tourDTO.getHotel() == null){
            throw new HotelNotExistsException();
        }
        Tour dbTour = optionalTour.get();
        if(dbTour.getName().compareTo(tourDTO.getName()) != 0) {
            if (tourRepository.existsByName(tourDTO.getName())) {
                throw new TourNameAlreadyExistsException();
            }
        }
        if(tourDTO.getDateArrival().compareTo(tourDTO.getDateDaparture()) == 0 ||
                tourDTO.getDateArrival().isBefore(tourDTO.getDateDaparture())){
            throw new DateException();
        }
        Tour tour = tourMapper.mapTourDTOToTour(tourDTO);
        tour.setId(dbTour.getId());
        tour.setPlaceCount(dbTour.getPlaceCount());
        tour.setBurning(dbTour.isBurning());
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    @Transactional
    public TourDTO updateTourBurning(int id, boolean burning) {
        log.info("[Service] updateTour with burning field");
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isEmpty()){
            throw new TourNotFoundException();
        }
        Tour tour = optionalTour.get();
        if(tour.getHotel() == null){
            throw new HotelNotExistsException();
        }
        tour.setBurning(burning);
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }

    @Override
    @Transactional
    public TourDTO updateTourMaxDisCount(int id, int maxDisCount) {
        log.info("[Service] updateTour with burning field");
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if(optionalTour.isEmpty()){
            throw new TourNotFoundException();
        }
        Tour tour = optionalTour.get();
        if(tour.getHotel() == null){
            throw new HotelNotExistsException();
        }
        tour.setMaxDisCount(maxDisCount);
        tour = tourRepository.save(tour);
        return tourMapper.mapTourToTourDTO(tour);
    }


    @Override
    @Transactional
    public void deleteTour(int tourId) {
        log.info("[Service] deleteTour with id {}", tourId);
        if(!tourRepository.existsById(tourId)){
            throw new TourNotFoundException();
        }
        tourRepository.deleteById(tourId);
    }

}
