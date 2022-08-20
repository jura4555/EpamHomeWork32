package com.epam.spring.homework4.controller;

import com.epam.spring.homework4.controller.dto.HotelDTO;
import com.epam.spring.homework4.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hotel")
    public List<HotelDTO> getAllHotel() {
        log.info("[Controller] receiving all hotel");
        return hotelService.getAllHotel();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hotel/{name}")
    public HotelDTO getHotelByName(@PathVariable(value = "name") String hotelName) {
        log.info("[Controller] getHolel by name {}", hotelName);
        return hotelService.getHotelByName(hotelName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hotel")
    public HotelDTO createHotel(@RequestBody @Valid HotelDTO hotelDTO) {
        log.info("[Controller] createHotel");
        return hotelService.createHotel(hotelDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/hotel/{hotelId}")
    public HotelDTO updateHotel(@PathVariable(value = "hotelId")int id, @RequestBody @Valid HotelDTO hotelDTO) {
        log.info("[Controller] updateHotel with all fields {}");
        return hotelService.updateHotel(id, hotelDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/hotel/{id}")
    public boolean deleteHotel(@PathVariable(value = "id") int hotelId) {
        log.info("[Controller] deleteHotel with id {}", hotelId);
        return hotelService.deleteHotel(hotelId);
    }

}
