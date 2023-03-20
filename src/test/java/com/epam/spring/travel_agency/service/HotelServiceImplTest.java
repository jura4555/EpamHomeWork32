package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.service.exception.HotelNameAndCityAlreadyExistsException;
import com.epam.spring.travel_agency.service.exception.HotelNotFoundException;
import com.epam.spring.travel_agency.service.impl.HotelServiceImpl;
import com.epam.spring.travel_agency.service.mapper.HotelMapper;
import com.epam.spring.travel_agency.service.model.Hotel;
import com.epam.spring.travel_agency.service.repository.HotelRepository;
import com.epam.spring.travel_agency.test.util.TestHotelDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void getHotelsTest(){
        Hotel hotel1 = TestHotelDataUtil.getHotel1();
        Hotel hotel2 = TestHotelDataUtil.getHotel2();
        HotelDTO hotelDTO1 = TestHotelDataUtil.getHotelDTO1();
        HotelDTO hotelDTO2 = TestHotelDataUtil.getHotelDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Hotel> hotels = new PageImpl<>(List.of(hotel1, hotel2), pageable, 2);
        when(hotelRepository.findAll(pageable)).thenReturn(hotels);
        when(hotelMapper.mapHotelToHotelDto(hotel1)).thenReturn(hotelDTO1);
        when(hotelMapper.mapHotelToHotelDto(hotel2)).thenReturn(hotelDTO2);
        Page<HotelDTO> result = hotelService.getAllHotel(1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(hotels.getTotalElements()));
        assertThat(result, contains(hotelDTO1, hotelDTO2));
    }

    @Test
    void getHotelTest(){
        Hotel hotel = TestHotelDataUtil.getHotel1();
        HotelDTO hotelDTO = TestHotelDataUtil.getHotelDTO1();
        when(hotelRepository.findByName(hotel.getName())).thenReturn(Optional.of(hotel));
        when(hotelMapper.mapHotelToHotelDto(hotel)).thenReturn(hotelDTO);
        HotelDTO result = hotelService.getHotelByName(hotel.getName());
        assertThat(result, allOf(
                hasProperty("id", equalTo(hotelDTO.getId())),
                hasProperty("name", equalTo(hotelDTO.getName())),
                hasProperty("city", equalTo(hotelDTO.getCity())),
                hasProperty("hotelType", equalTo(hotelDTO.getHotelType()))
        ));
    }

    @Test
    void getHotelWithNotFoundExceptionTest(){
        when(hotelRepository.findByName(anyString())).thenReturn(Optional.empty());
        assertThrows(HotelNotFoundException.class, () -> hotelService.getHotelByName(anyString()));
    }

    @Test
    void createHotelTest(){
        HotelDTO hotelDTO = TestHotelDataUtil.getHotelDTO1();
        Hotel hotel = TestHotelDataUtil.getHotel1();
        when(hotelMapper.mapHotelDtoToHotel(hotelDTO)).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        when(hotelMapper.mapHotelToHotelDto(hotel)).thenReturn(hotelDTO);
        HotelDTO result = hotelService.createHotel(hotelDTO);
        assertThat(result, allOf(
                hasProperty("id", equalTo(hotelDTO.getId())),
                hasProperty("name", equalTo(hotelDTO.getName())),
                hasProperty("city", equalTo(hotelDTO.getCity())),
                hasProperty("hotelType", equalTo(hotelDTO.getHotelType()))
        ));
    }

    @Test
    void createHotelWithHotelExistsExceptionTest(){
        HotelDTO hotelDTO = TestHotelDataUtil.getHotelDTO1();
        when(hotelRepository.existsByNameAndCity(hotelDTO.getName(), hotelDTO.getCity())).thenReturn(true);
        assertThrows(HotelNameAndCityAlreadyExistsException.class, () -> hotelService.createHotel(hotelDTO));
    }

    @Test
    void updateHotelTest(){
        Hotel dbHotel = TestHotelDataUtil.getHotel1();
        HotelDTO hotelDTO = TestHotelDataUtil.getUpdateHotelDTO();
        Hotel hotel = TestHotelDataUtil.getUpdateHotel();
        when(hotelRepository.findById(dbHotel.getId())).thenReturn(Optional.of(dbHotel));
        when(hotelMapper.mapHotelDtoToHotel(hotelDTO)).thenReturn(hotel);
        hotel.setId(dbHotel.getId());
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        hotelDTO.setId(hotel.getId());
        when(hotelMapper.mapHotelToHotelDto(hotel)).thenReturn(hotelDTO);
        HotelDTO result = hotelService.updateHotel(dbHotel.getId(), hotelDTO);
        assertThat(result, allOf(
                hasProperty("id", equalTo(dbHotel.getId())),
                hasProperty("name", equalTo(hotelDTO.getName())),
                hasProperty("city", equalTo(hotelDTO.getCity())),
                hasProperty("hotelType", equalTo(hotelDTO.getHotelType()))
        ));
    }

    @Test
    void updateHotelWithHotelNotFoundExceptionTest(){
        HotelDTO hotelDTO = TestHotelDataUtil.getUpdateHotelDTO();
        when(hotelRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
        assertThrows(HotelNotFoundException.class, () -> hotelService.updateHotel(anyInt(), hotelDTO));
    }

    @Test
    void updateHotelWithHotelExistsExceptionTest(){
        int inputId = TestHotelDataUtil.HOTEL_1_ID;
        HotelDTO hotelDTO = TestHotelDataUtil.getUpdateHotelDTO();
        Hotel hotel = TestHotelDataUtil.getHotel1();
        when(hotelRepository.findById(inputId)).thenReturn(Optional.of(hotel));
        when(hotelRepository.existsByNameAndCity(hotelDTO.getName(), hotelDTO.getCity())).thenReturn(true);
        assertThrows(HotelNameAndCityAlreadyExistsException.class, () -> hotelService.updateHotel(inputId, hotelDTO));
    }

    @Test
    void deleteHotelTest(){
        int deletedHotelId = TestHotelDataUtil.HOTEL_1_ID;
        when(hotelRepository.existsById(deletedHotelId)).thenReturn(true);
        hotelService.deleteHotel(deletedHotelId);
        verify(hotelRepository, times(1)).deleteById(deletedHotelId);
    }

    @Test
    void deleteHotelWithHotelExistsExceptionTest(){
        when(hotelRepository.existsById(anyInt())).thenReturn(false);
        assertThrows(HotelNotFoundException.class, () -> hotelService.deleteHotel(anyInt()));
        verify(hotelRepository, times(1)).existsById(anyInt());
    }

}
