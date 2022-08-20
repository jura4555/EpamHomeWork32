package com.epam.spring.homework4.service.repository.impl;

import com.epam.spring.homework4.service.exception.NotFoundException;
import com.epam.spring.homework4.service.model.Hotel;
import com.epam.spring.homework4.service.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class HotelRepositoryImpl implements HotelRepository {

    private int id;
    private final List<Hotel> hotels = new ArrayList<>();

    @Override
    public List<Hotel> getAllHotel() {
        log.info("[Repository] successfully received hotels");
        return new ArrayList<>(hotels);
    }

    @Override
    public Hotel getHotelByName(String hotelName) {
        log.info("[Repository] getHotel by hotelName {} ", hotelName);
        return hotels.stream()
                .filter(h -> h.getName().equals(hotelName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("[Repository] Hotel is not found!"));
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        log.info("[Repository] createHotel " + hotel.getName());
        id++;
        hotel.setId(id);
        hotels.add(hotel);
        log.info("[Repository] successfully created hotel with id:{}", hotel.getId());
        return hotel;
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        log.info("[Repository] updateHotel by all field");
        boolean isDeleted = hotels.removeIf(h -> h.getId() == hotel.getId());
        if(isDeleted) {
            hotels.add(hotel);
            log.info("[Repository] updateHotel is done in hotel:" + hotel.getId() + " " + hotel.getName());
        }
        else{
            throw new NotFoundException("[Repository] Hotel is not found!");
        }
        return hotel;
    }

    @Override
    public boolean deleteHotel(int hotelId) {
        log.info("[Repository] deleteHotel by id {} ", hotelId);
        return hotels.removeIf(h -> h.getId() == hotelId);
    }
}
