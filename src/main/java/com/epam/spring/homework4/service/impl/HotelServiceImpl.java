package com.epam.spring.homework4.service.impl;

import com.epam.spring.homework4.controller.dto.HotelDTO;
import com.epam.spring.homework4.service.HotelService;
import com.epam.spring.homework4.service.mapper.HotelMapper;
import com.epam.spring.homework4.service.model.Hotel;
import com.epam.spring.homework4.service.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelDTO> getAllHotel() {
        log.info("[Service] receiving all hotel");
        return hotelRepository.getAllHotel().stream()
                .map(hotelMapper::mapHotelToHotelDto)
                .sorted(Comparator.comparing(HotelDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getHotelByName(String hotelName) {
        log.info("[Service] getHolel by name {}", hotelName);
        Hotel hotel = hotelRepository.getHotelByName(hotelName);
        return hotelMapper.mapHotelToHotelDto(hotel);
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        log.info("[Service] createHotel");
        Hotel hotel = hotelMapper.mapHotelDtoToHotel(hotelDTO);
        hotel = hotelRepository.createHotel(hotel);
        return hotelMapper.mapHotelToHotelDto(hotel);
    }

    @Override
    public HotelDTO updateHotel(int id, HotelDTO hotelDTO) {
        log.info("[Service] updateHotel with all fields {}");
        Hotel hotel = hotelMapper.mapHotelDtoToHotel(hotelDTO);
        hotel.setId(id);
        hotel = hotelRepository.updateHotel(hotel);
        return hotelMapper.mapHotelToHotelDto(hotel);
    }

    @Override
    public boolean deleteHotel(int hotelId) {
        log.info("[Service] deleteHotel with id {}", hotelId);
        return hotelRepository.deleteHotel(hotelId);
    }
}
