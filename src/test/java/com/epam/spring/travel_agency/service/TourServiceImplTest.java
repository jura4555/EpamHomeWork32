package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.service.exception.*;
import com.epam.spring.travel_agency.service.impl.TourServiceImpl;
import com.epam.spring.travel_agency.service.mapper.TourMapper;
import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import com.epam.spring.travel_agency.service.repository.TourRepository;
import com.epam.spring.travel_agency.test.util.TestTourDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TourServiceImplTest {

    @Mock
    private TourRepository tourRepository;

    @Mock
    private TourMapper tourMapper;

    @InjectMocks
    private TourServiceImpl tourService;

    @Test
    void getAllTourTest(){
        Tour tour1 = TestTourDataUtil.getTour1();
        Tour tour2 = TestTourDataUtil.getTour2();
        TourDTO tourDTO1 = TestTourDataUtil.getTourDTO1();
        TourDTO tourDTO2 = TestTourDataUtil.getTourDTO2();
        when(tourRepository.findAll()).thenReturn(List.of(tour2, tour1));
        Pageable pageableAll = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Tour> tours = new PageImpl<>(List.of(tour1, tour2), pageableAll, 2);
        when(tourRepository.findAll(pageableAll)).thenReturn(tours);
        when(tourMapper.mapTourToTourDTO(tour1)).thenReturn(tourDTO1);
        when(tourMapper.mapTourToTourDTO(tour2)).thenReturn(tourDTO2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<TourDTO> testResult = new PageImpl<>(List.of(tourDTO1, tourDTO2), pageable, 2);
        Page<TourDTO> result = tourService.getAllTour(1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(testResult.getTotalElements()));
        assertThat(result, contains(tourDTO1, tourDTO2));
    }

    @Test
    void getTourTest(){
        Tour tour = TestTourDataUtil.getTour1();
        TourDTO tourDTO = TestTourDataUtil.getTourDTO1();
        when(tourRepository.findByName(tour.getName())).thenReturn(Optional.of(tour));
        when(tourMapper.mapTourToTourDTO(tour)).thenReturn(tourDTO);
        TourDTO result = tourService.getTourByName(tour.getName());
        assertThat(result, allOf(
                hasProperty("id", equalTo(tourDTO.getId())),
                hasProperty("name", equalTo(tourDTO.getName())),
                hasProperty("price", equalTo(tourDTO.getPrice())),
                hasProperty("dateDaparture", equalTo(tourDTO.getDateDaparture())),
                hasProperty("dateArrival", equalTo(tourDTO.getDateArrival())),
                hasProperty("placeDaparture", equalTo(tourDTO.getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(tourDTO.getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(tourDTO.getMaxDisCount())),
                hasProperty("placeCount", equalTo(tourDTO.getPlaceCount())),
                hasProperty("tourType", equalTo(tourDTO.getTourType())),
                hasProperty("burning", equalTo(tourDTO.isBurning()))
                ));
        assertThat(result.getHotel(), allOf(
                hasProperty("id", equalTo(tourDTO.getHotel().getId())),
                hasProperty("name", equalTo(tourDTO.getHotel().getName())),
                hasProperty("city", equalTo(tourDTO.getHotel().getCity())),
                hasProperty("hotelType", equalTo(tourDTO.getHotel().getHotelType()))
        ));
    }

    @Test
    void getTourWithNotFoundExceptionTest(){
        Tour tour = TestTourDataUtil.getTour1();
        when(tourRepository.findByName(tour.getName())).thenReturn(Optional.empty());
        assertThrows(TourNotFoundException.class, () -> tourService.getTourByName(tour.getName()));
    }

    @Test
    void getAllTourByTourTypeTest(){
        Tour tour1 = TestTourDataUtil.getTour1();
        Tour tour2 = TestTourDataUtil.getTour2();
        TourDTO tourDTO1 = TestTourDataUtil.getTourDTO1();
        TourDTO tourDTO2 = TestTourDataUtil.getTourDTO2();
        when(tourRepository.findByTourType(TourType.REST)).thenReturn(List.of(tour2, tour1));
        Pageable pageableAll = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Tour> tours = new PageImpl<>(List.of(tour1, tour2), pageableAll, 2);
        when(tourRepository.findByTourType(TourType.REST, pageableAll)).thenReturn(tours);
        when(tourMapper.mapTourToTourDTO(tour1)).thenReturn(tourDTO1);
        when(tourMapper.mapTourToTourDTO(tour2)).thenReturn(tourDTO2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<TourDTO> testResult = new PageImpl<>(List.of(tourDTO1, tourDTO2), pageable, 2);
        Page<TourDTO> result = tourService.getTourByTourType(TourType.REST, 1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(testResult.getTotalElements()));
        assertThat(result, contains(tourDTO1, tourDTO2));
    }


    @Test
    void getAllTourByPlaceCountTest(){
        Tour tour1 = TestTourDataUtil.getTour1();
        TourDTO tourDTO1 = TestTourDataUtil.getTourDTO1();
        when(tourRepository.findByPlaceCount(5)).thenReturn(List.of(tour1));
        Pageable pageableAll = PageRequest.of(0, 1, Sort.by("id").ascending());
        Page<Tour> tours = new PageImpl<>(List.of(tour1), pageableAll, 1);
        when(tourRepository.findByPlaceCount(5, pageableAll)).thenReturn(tours);
        when(tourMapper.mapTourToTourDTO(tour1)).thenReturn(tourDTO1);
        Pageable pageable = PageRequest.of(0, 1);
        Page<TourDTO> testResult = new PageImpl<>(List.of(tourDTO1), pageable, 1);
        Page<TourDTO> result = tourService.getTourByPlaceCount(5, 1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(testResult.getTotalElements()));
        assertThat(result, contains(tourDTO1));
    }

    @Test
    void getAllTourByPriceTest(){
        Tour tour1 = TestTourDataUtil.getTour1();
        Tour tour2 = TestTourDataUtil.getTour2();
        TourDTO tourDTO1 = TestTourDataUtil.getTourDTO1();
        TourDTO tourDTO2 = TestTourDataUtil.getTourDTO2();
        when(tourRepository.findByPrice(1000, 3200)).thenReturn(List.of(tour1, tour2));
        Pageable pageableAll = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Tour> tours = new PageImpl<>(List.of(tour1, tour2), pageableAll, 2);
        when(tourRepository.findByPrice(1000, 3200, pageableAll)).thenReturn(tours);
        when(tourMapper.mapTourToTourDTO(tour1)).thenReturn(tourDTO1);
        when(tourMapper.mapTourToTourDTO(tour2)).thenReturn(tourDTO2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<TourDTO> testResult = new PageImpl<>(List.of(tourDTO1, tourDTO2), pageable, 2);
        Page<TourDTO> result = tourService.getTourByPrice(1000, 3200, 1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(testResult.getTotalElements()));
        assertThat(result, contains(tourDTO1, tourDTO2));
    }

    @Test
    void getAllTourByHotelTypeTest(){
        Tour tour1 = TestTourDataUtil.getTour1();
        Tour tour2 = TestTourDataUtil.getTour2();
        TourDTO tourDTO1 = TestTourDataUtil.getTourDTO1();
        TourDTO tourDTO2 = TestTourDataUtil.getTourDTO2();
        when(tourRepository.findByHotelType(HotelType.TOURIST_CLASS)).thenReturn(List.of(tour1, tour2));
        Pageable pageableAll = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Tour> tours = new PageImpl<>(List.of(tour1, tour2), pageableAll, 2);
        when(tourRepository.findByHotelType(HotelType.TOURIST_CLASS, pageableAll)).thenReturn(tours);
        when(tourMapper.mapTourToTourDTO(tour1)).thenReturn(tourDTO1);
        when(tourMapper.mapTourToTourDTO(tour2)).thenReturn(tourDTO2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<TourDTO> testResult = new PageImpl<>(List.of(tourDTO1, tourDTO2), pageable, 2);
        Page<TourDTO> result = tourService.getTourByHotelType(HotelType.TOURIST_CLASS, 1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(testResult.getTotalElements()));
        assertThat(result, contains(tourDTO1, tourDTO2));
    }

    @Test
    void createTourTest(){
        Tour tour = TestTourDataUtil.getTourForCreate();
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForCreate();
        when(tourMapper.mapTourDTOToTour(tourDTO)).thenReturn(tour);
        when(tourRepository.save(tour)).thenReturn(tour);
        when(tourMapper.mapTourToTourDTO(tour)).thenReturn(tourDTO);
        TourDTO result = tourService.createTour(tourDTO);
        assertThat(result, allOf(
                hasProperty("id", equalTo(tourDTO.getId())),
                hasProperty("name", equalTo(tourDTO.getName())),
                hasProperty("price", equalTo(tourDTO.getPrice())),
                hasProperty("dateDaparture", equalTo(tourDTO.getDateDaparture())),
                hasProperty("dateArrival", equalTo(tourDTO.getDateArrival())),
                hasProperty("placeDaparture", equalTo(tourDTO.getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(tourDTO.getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(tourDTO.getMaxDisCount())),
                hasProperty("placeCount", equalTo(tourDTO.getPlaceCount())),
                hasProperty("tourType", equalTo(tourDTO.getTourType())),
                hasProperty("burning", equalTo(tourDTO.isBurning()))
        ));
        assertThat(result.getHotel(), allOf(
                hasProperty("id", equalTo(tourDTO.getHotel().getId())),
                hasProperty("name", equalTo(tourDTO.getHotel().getName())),
                hasProperty("city", equalTo(tourDTO.getHotel().getCity())),
                hasProperty("hotelType", equalTo(tourDTO.getHotel().getHotelType()))
        ));
    }

    @Test
    void createTourWithTourNameAlreadyExistsExceptionTest(){
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForCreate();
        when(tourRepository.existsByName(tourDTO.getName())).thenReturn(true);
        assertThrows(TourNameAlreadyExistsException.class, () -> tourService.createTour(tourDTO));
    }

    @Test
    void createTourWithHotelNotExistsExceptionTest(){
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForCreate();
        tourDTO.setHotel(null);
        assertThrows(HotelNotExistsException.class, () -> tourService.createTour(tourDTO));
    }

    @Test
    void createTourWithDateExceptionTest(){
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForCreate();
        tourDTO.setDateArrival( LocalDate.of(2023, 06, 10));
        assertThrows(DateException.class, () -> tourService.createTour(tourDTO));
    }

    @Test
    void updateTourTest(){
        Tour dbTour = TestTourDataUtil.getTour1();
        Tour tour = TestTourDataUtil.getTourForUpdate();
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForUpdate();
        when(tourRepository.findById(dbTour.getId())).thenReturn(Optional.of(dbTour));
        when(tourMapper.mapTourDTOToTour(tourDTO)).thenReturn(tour);
        tourDTO.setId(dbTour.getId());
        tourDTO.setPlaceCount(dbTour.getPlaceCount());
        tourDTO.setBurning(dbTour.isBurning());
        tour.setId(dbTour.getId());
        tour.setPlaceCount(dbTour.getPlaceCount());
        tour.setBurning(dbTour.isBurning());
        when(tourRepository.save(tour)).thenReturn(tour);
        when(tourMapper.mapTourToTourDTO(tour)).thenReturn(tourDTO);
        TourDTO result = tourService.updateTour(dbTour.getId(), tourDTO);
        assertThat(result, allOf(
                hasProperty("id", equalTo(tourDTO.getId())),
                hasProperty("name", equalTo(tourDTO.getName())),
                hasProperty("price", equalTo(tourDTO.getPrice())),
                hasProperty("dateDaparture", equalTo(tourDTO.getDateDaparture())),
                hasProperty("dateArrival", equalTo(tourDTO.getDateArrival())),
                hasProperty("placeDaparture", equalTo(tourDTO.getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(tourDTO.getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(tourDTO.getMaxDisCount())),
                hasProperty("placeCount", equalTo(tourDTO.getPlaceCount())),
                hasProperty("tourType", equalTo(tourDTO.getTourType())),
                hasProperty("burning", equalTo(tourDTO.isBurning()))
        ));
        assertThat(result.getHotel(), allOf(
                hasProperty("id", equalTo(tourDTO.getHotel().getId())),
                hasProperty("name", equalTo(tourDTO.getHotel().getName())),
                hasProperty("city", equalTo(tourDTO.getHotel().getCity())),
                hasProperty("hotelType", equalTo(tourDTO.getHotel().getHotelType()))
        ));
    }

    @Test
    void updateTourWithTourNotFoundExceptionTest(){
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForUpdate();
        when(tourRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(TourNotFoundException.class, () -> tourService.updateTour(anyInt(), tourDTO));
    }

    @Test
    void updateTourWithHotelNotExistsExceptionTest(){
        int inputId = TestTourDataUtil.TOUR_1_ID;
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForUpdate();
        tourDTO.setHotel(null);
        when(tourRepository.findById(inputId)).thenReturn(Optional.of(TestTourDataUtil.getTour1()));
        assertThrows(HotelNotExistsException.class, () -> tourService.updateTour(inputId, tourDTO));
    }

    @Test
    void updateTourWithTourNameAlreadyExistsExceptionTest(){
        int inputId = TestTourDataUtil.TOUR_1_ID;
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForUpdate();
        when(tourRepository.findById(inputId)).thenReturn(Optional.of(TestTourDataUtil.getTour1()));
        when(tourRepository.existsByName(tourDTO.getName())).thenReturn(true);
        assertThrows(TourNameAlreadyExistsException.class, () -> tourService.updateTour(inputId, tourDTO));
    }

    @Test
    void updateTourWithDateExceptionTest(){
        int inputId = TestTourDataUtil.TOUR_1_ID;
        TourDTO tourDTO = TestTourDataUtil.getTourDTOForUpdate();
        tourDTO.setDateArrival( LocalDate.of(2023, 06, 10));
        when(tourRepository.findById(inputId)).thenReturn(Optional.of(TestTourDataUtil.getTour1()));
        assertThrows(DateException.class, () -> tourService.updateTour(inputId, tourDTO));
    }

    @Test
    void updateTourBurningTest(){
        Boolean newBurning = false;
        Tour tour = TestTourDataUtil.getTour1();
        TourDTO tourDTO = TestTourDataUtil.getTourDTO1();
        when(tourRepository.findById(tour.getId())).thenReturn(Optional.of(tour));
        tour.setBurning(newBurning);
        tourDTO.setBurning(newBurning);
        when(tourRepository.save(tour)).thenReturn(tour);
        when(tourMapper.mapTourToTourDTO(tour)).thenReturn(tourDTO);
        TourDTO result = tourService.updateTourBurning(tour.getId(), newBurning);
        assertThat(result, allOf(
                hasProperty("id", equalTo(tourDTO.getId())),
                hasProperty("name", equalTo(tourDTO.getName())),
                hasProperty("price", equalTo(tourDTO.getPrice())),
                hasProperty("dateDaparture", equalTo(tourDTO.getDateDaparture())),
                hasProperty("dateArrival", equalTo(tourDTO.getDateArrival())),
                hasProperty("placeDaparture", equalTo(tourDTO.getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(tourDTO.getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(tourDTO.getMaxDisCount())),
                hasProperty("placeCount", equalTo(tourDTO.getPlaceCount())),
                hasProperty("tourType", equalTo(tourDTO.getTourType())),
                hasProperty("burning", equalTo(tourDTO.isBurning()))
        ));
        assertThat(result.getHotel(), allOf(
                hasProperty("id", equalTo(tourDTO.getHotel().getId())),
                hasProperty("name", equalTo(tourDTO.getHotel().getName())),
                hasProperty("city", equalTo(tourDTO.getHotel().getCity())),
                hasProperty("hotelType", equalTo(tourDTO.getHotel().getHotelType()))
        ));
    }

    @Test
    void updateTourBurningWithTourNotFoundExceptionTest(){
        when(tourRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(TourNotFoundException.class, () -> tourService.updateTourBurning(anyInt(), false));
    }

    @Test
    void updateTourBurningWithHotelNotExistsExceptionTest(){
        int inputId = TestTourDataUtil.TOUR_1_ID;
        Tour tour = TestTourDataUtil.getTour1();
        tour.setHotel(null);
        when(tourRepository.findById(inputId)).thenReturn(Optional.of(tour));
        assertThrows(HotelNotExistsException.class, () -> tourService.updateTourBurning(inputId, false));
    }

    @Test
    void updateTourMaxDisCountTest(){
        int newMaxDisCount = TestTourDataUtil.TOUR_UPDATE_MAX_DISCOUNT;
        Tour tour = TestTourDataUtil.getTour2();
        TourDTO tourDTO = TestTourDataUtil.getTourDTO2();
        when(tourRepository.findById(tour.getId())).thenReturn(Optional.of(tour));
        tour.setMaxDisCount(newMaxDisCount);
        tourDTO.setMaxDisCount(newMaxDisCount);
        when(tourRepository.save(tour)).thenReturn(tour);
        when(tourMapper.mapTourToTourDTO(tour)).thenReturn(tourDTO);
        TourDTO result = tourService.updateTourMaxDisCount(tour.getId(), newMaxDisCount);
        assertThat(result, allOf(
                hasProperty("id", equalTo(tourDTO.getId())),
                hasProperty("name", equalTo(tourDTO.getName())),
                hasProperty("price", equalTo(tourDTO.getPrice())),
                hasProperty("dateDaparture", equalTo(tourDTO.getDateDaparture())),
                hasProperty("dateArrival", equalTo(tourDTO.getDateArrival())),
                hasProperty("placeDaparture", equalTo(tourDTO.getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(tourDTO.getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(tourDTO.getMaxDisCount())),
                hasProperty("placeCount", equalTo(tourDTO.getPlaceCount())),
                hasProperty("tourType", equalTo(tourDTO.getTourType())),
                hasProperty("burning", equalTo(tourDTO.isBurning()))
        ));
        assertThat(result.getHotel(), allOf(
                hasProperty("id", equalTo(tourDTO.getHotel().getId())),
                hasProperty("name", equalTo(tourDTO.getHotel().getName())),
                hasProperty("city", equalTo(tourDTO.getHotel().getCity())),
                hasProperty("hotelType", equalTo(tourDTO.getHotel().getHotelType()))
        ));
    }

    @Test
    void updateTourMaxDisCountWithTourNotFoundExceptionTest(){
        when(tourRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(TourNotFoundException.class, () -> tourService.updateTourMaxDisCount(anyInt(), 40));
    }

    @Test
    void updateTourMaxDisCountWithHotelNotExistsExceptionTest(){
        int inputId = TestTourDataUtil.TOUR_1_ID;
        Tour tour = TestTourDataUtil.getTour1();
        tour.setHotel(null);
        when(tourRepository.findById(inputId)).thenReturn(Optional.of(tour));
        assertThrows(HotelNotExistsException.class, () -> tourService.updateTourMaxDisCount(inputId, 30));
    }

    @Test
    void deleteTourTest(){
        int inputId = TestTourDataUtil.TOUR_1_ID;
        when(tourRepository.existsById(inputId)).thenReturn(true);
        tourService.deleteTour(inputId);
        verify(tourRepository, times(1)).deleteById(inputId);
    }

    @Test
    void deleteTourWithHotelExistsExceptionTest(){
        when(tourRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(TourNotFoundException.class, () -> tourService.deleteTour(anyInt()));
        verify(tourRepository, times(1)).existsById(anyInt());
    }

}
