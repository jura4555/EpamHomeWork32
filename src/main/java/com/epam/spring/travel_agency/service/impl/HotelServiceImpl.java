package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.service.HotelService;
import com.epam.spring.travel_agency.service.exception.HotelNameAndCityAlreadyExistsException;
import com.epam.spring.travel_agency.service.exception.HotelNotFoundException;
import com.epam.spring.travel_agency.service.mapper.HotelMapper;
import com.epam.spring.travel_agency.service.model.Hotel;
import com.epam.spring.travel_agency.service.repository.HotelRepository;
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
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelDTO> getAllHotel() {
        log.info("[Service] receiving all hotel");
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(hotelMapper::mapHotelToHotelDto)
                .sorted(Comparator.comparing(HotelDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getHotelByName(String hotelName) {
        log.info("[Service] getHolel by name {}", hotelName);
        Hotel hotel = hotelRepository.findByName(hotelName).orElseThrow(HotelNotFoundException::new);;
        return hotelMapper.mapHotelToHotelDto(hotel);
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        log.info("[Service] createHotel");
        if(hotelRepository.existsByNameAndCity(hotelDTO.getName(), hotelDTO.getCity())){
            throw new HotelNameAndCityAlreadyExistsException();
        }
        Hotel hotel = hotelMapper.mapHotelDtoToHotel(hotelDTO);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.mapHotelToHotelDto(hotel);
    }

    @Override
    public HotelDTO updateHotel(int id, HotelDTO hotelDTO) {
        log.info("[Service] updateHotel with all fields {}");
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if(optionalHotel.isEmpty()){
            throw new HotelNotFoundException();
        }
        Hotel dbHotel = optionalHotel.get();
        String dbData = dbHotel.getName() + " " + dbHotel.getCity();
        String DTOData = hotelDTO.getName() + " " + hotelDTO.getCity();
        if(dbData.compareTo(DTOData) != 0) {
            if(hotelRepository.existsByNameAndCity(hotelDTO.getName(), hotelDTO.getCity())){
                throw new HotelNameAndCityAlreadyExistsException();
            }
        }
        Hotel hotel = hotelMapper.mapHotelDtoToHotel(hotelDTO);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.mapHotelToHotelDto(hotel);
    }

    @Override
    public void deleteHotel(int hotelId) {
        log.info("[Service] deleteHotel with id {}", hotelId);
        if(!hotelRepository.existsById(hotelId)){
            throw new HotelNotFoundException();
        }
        hotelRepository.deleteById(hotelId);
    }
}
