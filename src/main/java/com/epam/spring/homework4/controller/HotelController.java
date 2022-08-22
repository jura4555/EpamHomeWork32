package com.epam.spring.homework4.controller;

import com.epam.spring.homework4.controller.api.HotelAPI;
import com.epam.spring.homework4.controller.dto.HotelDTO;
import com.epam.spring.homework4.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HotelController implements HotelAPI {

    private final HotelService hotelService;

    @Override
    public List<HotelDTO> getAllHotel() {
        log.info("[Controller] receiving all hotel");
        return hotelService.getAllHotel();
    }

    @Override
    public HotelDTO getHotelByName(String name) {
        log.info("[Controller] getHolel by name {}", name);
        return hotelService.getHotelByName(name);
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        log.info("[Controller] createHotel");
        return hotelService.createHotel(hotelDTO);
    }

    @Override
    public HotelDTO updateHotel(int id, HotelDTO hotelDTO) {
        log.info("[Controller] updateHotel with all fields {}");
        return hotelService.updateHotel(id, hotelDTO);
    }

    @Override
    public boolean deleteHotel(int id) {
        log.info("[Controller] deleteHotel with id {}", id);
        return hotelService.deleteHotel(id);
    }

}
