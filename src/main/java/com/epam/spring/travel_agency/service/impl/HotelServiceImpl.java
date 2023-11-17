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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public Page<HotelDTO> getAllHotel(int page, int size, String sortBy, String order) {
        log.info("[Service] receiving all hotel");
        Pageable pageable = PageRequest.of(page - 1, size,
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());
        Page<HotelDTO> hotels = hotelRepository.findAll(pageable).map(hotelMapper::mapHotelToHotelDto);
        return hotels;
    }

    @Override
    public HotelDTO getHotelByName(String hotelName) {
        log.info("[Service] getHolel by name {}", hotelName);
        Hotel hotel = hotelRepository.findByName(hotelName).orElseThrow(HotelNotFoundException::new);;
        return hotelMapper.mapHotelToHotelDto(hotel);
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void deleteHotel(int hotelId) {
        log.info("[Service] deleteHotel with id {}", hotelId);
        if(!hotelRepository.existsById(hotelId)){
            throw new HotelNotFoundException();
        }
        hotelRepository.deleteById(hotelId);
    }
}
